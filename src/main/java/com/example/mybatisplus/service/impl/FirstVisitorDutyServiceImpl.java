package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.mapper.FirstVisitRecordMapper;
import com.example.mybatisplus.mapper.PersonMapper;
import com.example.mybatisplus.model.domain.FirstVisitRecord;
import com.example.mybatisplus.model.domain.FirstVisitorDuty;
import com.example.mybatisplus.mapper.FirstVisitorDutyMapper;
import com.example.mybatisplus.model.domain.Person;
import com.example.mybatisplus.model.domain.TimePeriod;
import com.example.mybatisplus.model.vo.FirstVisitorDutyVO;
import com.example.mybatisplus.service.FirstVisitorDutyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.service.TimePeriodService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-15
 */

@Service
public class FirstVisitorDutyServiceImpl extends ServiceImpl<FirstVisitorDutyMapper, FirstVisitorDuty> implements FirstVisitorDutyService {

    @Autowired
    FirstVisitorDutyMapper firstVisitorDutyMapper;

    @Autowired
    FirstVisitRecordMapper firstVisitRecordMapper;

    @Autowired
    PersonMapper personMapper;

    @Autowired
    TimePeriodService timePeriodService;


    @Override
    public List<FirstVisitorDutyVO> getAllFVDuty() {
        return firstVisitorDutyMapper.getAllFVDuty();
    }

    @Override
    public List<FirstVisitorDutyVO> getAvailableFVDuty() {
        return firstVisitorDutyMapper.getAvailableFVDuty();
    }


    /**
     * 描述：是否地点冲突
     *
     */
    @Override
    public Boolean isConflic(Integer tpID, Long lID) {
        QueryWrapper<FirstVisitorDuty> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FirstVisitorDuty::getTpId,tpID)
                .eq(FirstVisitorDuty::getLocationId,lID);
        List<FirstVisitorDuty> firstVisitorDuties = firstVisitorDutyMapper.selectList(wrapper);
        return !firstVisitorDuties.isEmpty();
    }


    /**
     * 描述：修改初访员排班地点
     *
     */
    @Override
    public JsonResponse alterFVDuty(Long fvdID, Integer tpID, Long fvID, Long lID) {
        //地点是否冲突
        if(isConflic(tpID,lID))
            return JsonResponse.failure("地点冲突!");

        //更新排班表
        UpdateWrapper<FirstVisitorDuty> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(FirstVisitorDuty::getFvdId,fvdID)
                .set(FirstVisitorDuty::getLocationId,lID);
        firstVisitorDutyMapper.update(null,wrapper);

        //更新排班对应的未完成初访预约的地点信息
        UpdateWrapper<FirstVisitRecord> wrapper2 = new UpdateWrapper<>();
        wrapper2.lambda().eq(FirstVisitRecord::getTpId,tpID)
                .eq(FirstVisitRecord::getFvId,fvID)
                .eq(FirstVisitRecord::getIsFinished,0)
                .set(FirstVisitRecord::getLocationId,lID);
        firstVisitRecordMapper.update(null,wrapper2);
        return JsonResponse.successMessage("修改成功,请通知该排班下未完成初访的同学地点变动!");
    }


    /**
     * 描述：中心管理员删除初访员排班
     *
     */
    @Override
    public JsonResponse deleteFVDuty(Long fvdID, Integer tpID, Long fvID) {
        //查询是否有未完成初访预约
        QueryWrapper<FirstVisitRecord> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FirstVisitRecord::getFvId,fvID)
                .eq(FirstVisitRecord::getTpId,tpID)
                .eq(FirstVisitRecord::getIsFinished,0);
        List<FirstVisitRecord> firstVisitRecords = firstVisitRecordMapper.selectList(wrapper);

        QueryWrapper<Person> wrapper2 = new QueryWrapper<>();
        wrapper2.lambda().eq(Person::getPId,fvID);
        Person person = personMapper.selectOne(wrapper2);

        QueryWrapper<TimePeriod> wrapper3 = new QueryWrapper<>();
        wrapper3.lambda().eq(TimePeriod::getTpId,tpID);
        TimePeriod timePeriod = timePeriodService.getOne(wrapper3);

        firstVisitorDutyMapper.deleteById(fvdID);
        String message = "";
        if (firstVisitRecords.isEmpty()) {
            message = "删除成功!";
        }else{
            message = "删除成功，还有未完成的咨询预约，请对初访员："
                    + person.getName() + " 周" + timePeriod.getWeekday() + " "
                    + timePeriod.getStartTime() + "的初访预约信息进行修改，并通知学生相关变动。";
        }

        return JsonResponse.successMessage(message);
    }


    /**
     * 描述：中心管理员新增初访员排班
     *
     */
    @Override
    public JsonResponse insertFVDuty(Integer tpID, Long fvID, Long lID) {
        //查询地点是否冲突
        if(isConflic(tpID,lID))
            return JsonResponse.failure("地点冲突！");

        FirstVisitorDuty f = new FirstVisitorDuty();
        f.setFvId(fvID)
                .setLocationId(lID)
                .setTpId(tpID);
        firstVisitorDutyMapper.insert(f);
        return JsonResponse.successMessage("新增成功!");
    }

    /*
     * 刷新初访员排班情况
     * 将is_available字段置为1
     */
    @Override
    public void refreshDuty() {
        UpdateWrapper<FirstVisitorDuty> wrapper = new UpdateWrapper<>();
        wrapper.lambda().set(FirstVisitorDuty::getIsAvailable,1);
        baseMapper.update(null,wrapper);
    }

    /*
     * 描述：中心管理员获得某时间段时空闲的初访员
     *
     */
    @Override
    public List<FirstVisitorDutyVO> getAvailableFVByTimePeriod(int tpId) {
        return firstVisitorDutyMapper.getAvailableFVDutyInTp(tpId);
    }
}
