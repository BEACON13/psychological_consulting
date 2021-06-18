package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.mapper.ConsultAppointmentRecordMapper;
import com.example.mybatisplus.mapper.TimePeriodMapper;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.example.mybatisplus.model.domain.ConsultantDuty;
import com.example.mybatisplus.mapper.ConsultantDutyMapper;
import com.example.mybatisplus.model.domain.TimePeriod;
import com.example.mybatisplus.model.vo.ConsultantDutyVO;
import com.example.mybatisplus.service.ConsultantDutyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.service.TimePeriodService;
import org.apache.tomcat.jni.Local;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-17
 */
@Service
public class ConsultantDutyServiceImpl extends ServiceImpl<ConsultantDutyMapper, ConsultantDuty> implements ConsultantDutyService {

    @Autowired
    ConsultantDutyMapper consultantDutyMapper;
    @Autowired
    TimePeriodService timePeriodService;
    @Autowired
    ConsultAppointmentRecordMapper appointmentRecordMapper;

    /**
     * 描述：判断咨询师的空闲时间是否在今日之前
     *
     */
    @Override
    public void checkFreeTime(Integer tpID,Long cID) {
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        date.format(formatter);

        QueryWrapper<ConsultantDuty> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultantDuty::getTpId,tpID).eq(ConsultantDuty::getCId,cID);
        ConsultantDuty consultantDuty = consultantDutyMapper.selectOne(wrapper);

        TimePeriod byId = timePeriodService.getById(consultantDuty.getTpId());
        Integer tpWeek = byId.getWeekday();

        if(consultantDuty.getFreeTime().isBefore(date)){
            Integer weekday = date.getDayOfWeek().getValue();
            LocalDate newFreeTime;
            if(tpWeek > weekday)
                newFreeTime = date.plusDays(tpWeek - weekday);
            else
                newFreeTime = date.plusDays(7 - weekday + tpWeek);

            UpdateWrapper<ConsultantDuty> wrapper2 = new UpdateWrapper<>();
            wrapper2.lambda().eq(ConsultantDuty::getTpId,tpID)
                    .eq(ConsultantDuty::getCId,cID)
                    .set(ConsultantDuty::getFreeTime,newFreeTime);
            consultantDutyMapper.update(null,wrapper2);
        }

    }


    /**
     * 描述：显示该tp下，排班咨询师姓名及free_time
     *
     */
    @Override
    public JsonResponse showFreeTime(Integer tpID) {
        QueryWrapper<ConsultantDuty> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultantDuty::getTpId,tpID);
        List<ConsultantDuty> consultantDuties = consultantDutyMapper.selectList(wrapper);

        for(ConsultantDuty c : consultantDuties){
            checkFreeTime(c.getTpId(),c.getCId());
        }

        List<ConsultantDutyVO> consultantDutyVOS = consultantDutyMapper.showFreeTime(tpID);
        return JsonResponse.success(consultantDutyVOS,"success!");
    }


    /**
     * 描述：中心管理员查看咨询师排班
     *
     */
    @Override
    public JsonResponse showConsultantDuty() {
        List<ConsultantDutyVO> consultantDutyVOS = consultantDutyMapper.showConsultantDuty();
        return JsonResponse.success(consultantDutyVOS,"success!");
    }


    /**
     * 描述：中心管理员更改咨询师排班地点
     *
     */
    @Override
    public JsonResponse alterConsultantDuty(Integer tpID,Long cID,Long lID) {
        //查询地点是否冲突
        QueryWrapper<ConsultantDuty> wrapper2 = new QueryWrapper<>();
        wrapper2.lambda().eq(ConsultantDuty::getTpId,tpID)
                .eq(ConsultantDuty::getLocationId,lID);
        List<ConsultantDuty> consultantDuties = consultantDutyMapper.selectList(wrapper2);
        if(!consultantDuties.isEmpty())
            return JsonResponse.failure("地点冲突！");

        //更新排班表
        UpdateWrapper<ConsultantDuty> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(ConsultantDuty::getTpId,tpID)
                .eq(ConsultantDuty::getCId,cID)
                .set(ConsultantDuty::getLocationId,lID);
        consultantDutyMapper.update(null,wrapper);

        //修改该排版对应的未完成的预约
        UpdateWrapper<ConsultAppointmentRecord> wrapper3 = new UpdateWrapper<>();
        wrapper3.lambda().eq(ConsultAppointmentRecord::getCId,cID)
                .eq(ConsultAppointmentRecord::getTpId,tpID)
                .eq(ConsultAppointmentRecord::getIsFinished,0)
                .set(ConsultAppointmentRecord::getLocationId,lID);
        appointmentRecordMapper.update(null,wrapper3);
        return JsonResponse.successMessage("修改成功,请通知该排版下未完成咨询的同学地点变动!");
    }

    @Override
    public JsonResponse deleteConsultantDuty(Long cdID, Integer tpID, Long cID) {
        return null;
    }
}
