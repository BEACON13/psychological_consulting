package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.model.domain.*;
import com.example.mybatisplus.mapper.ConsultApplyMapper;
import com.example.mybatisplus.model.vo.ConsultApplyVO;
import com.example.mybatisplus.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */
@Service
public class ConsultApplyServiceImpl extends ServiceImpl<ConsultApplyMapper, ConsultApply> implements ConsultApplyService {

    @Autowired
    StudentService studentService;
    @Autowired
    ConsultApplyMapper consultApplyMapper;
    @Autowired
    ConsultAppointmentRecordService cars;
    @Autowired
    FirstApplyService firstApplyService;
    @Autowired
    FirstVisitReportService firstVisitReportService;
    

    /**
     * 描述：学生是否能够申请咨询
     *
     */
    @Override
    public JsonResponse isAllowedtoApply() {
        Long id = SecurityUtils.getCurrentStudentInfo().getSId();
        //学生是否具备咨询资格，即is_qualified为1才能申请
        boolean flag1 = studentService.getById(id).getIsQualified();

        //学生是否有未完成都咨询申请，没有才可以申请
        QueryWrapper<ConsultApply> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultApply::getSId,id).eq(ConsultApply::getIsFinished,0);
        List<ConsultApply> consultApplies = consultApplyMapper.selectList(wrapper);
        boolean flag2 = consultApplies.isEmpty();

        //学生是否有未完成的咨询预约记录，没有才可以申请
        List<ConsultAppointmentRecord> recordsByStuId = cars.getRecordsByStuId(id);
        boolean flag3 = recordsByStuId.isEmpty();

        if(flag1&&flag2&&flag3)
            return JsonResponse.successMessage("可以申请！");

        return JsonResponse.failure("没有申请资格！");
    }

    /**
     * 描述：学生填写咨询申请
     *
     */
    @Override
    public JsonResponse applyConsult(Integer tp1, Integer tp2, Integer tp3) {
        Long id = SecurityUtils.getCurrentStudentInfo().getSId();

        List<FirstApply> firstApplyByStu = firstApplyService.getFirstApplyByStu(id);
        int n = firstApplyByStu.size();
        FirstApply f = firstApplyByStu.get(n-1);

        List<FirstVisitReport> fvReportsBySId = firstVisitReportService.getFVReportsBySId(id);
        int m = fvReportsBySId.size();
        FirstVisitReport fvr = fvReportsBySId.get(m-1);

        ConsultApply consultApply = new ConsultApply();
        consultApply.setSId(id)
                .setStuName(f.getName())
                .setPhone(f.getPhone())
                .setAddress(f.getAddress())
                .setEmergencyPhone(f.getEmergencyPhone())
                .setDangerLevel(fvr.getDangerLevel())
                .setProblemType(fvr.getProblemType())
                .setTpId1(tp1)
                .setTpId2(tp2)
                .setTpId3(tp3)
                .setNum(8);

        consultApplyMapper.insert(consultApply);
        return JsonResponse.successMessage("提交成功!");
    }


    /**
     * 描述：心理助理查看所有申请
     *
     */
    @Override
    public JsonResponse showAllApplies() {
        List<ConsultApplyVO> consultApplyVOS = consultApplyMapper.showAllApplies();

        return JsonResponse.success(consultApplyVOS,"success!");
    }


    /**
     * 描述：心理助理根据学生姓名查看申请
     *
     */
    @Override
    public JsonResponse showApplyByStuName(String stuName) {
        List<ConsultApplyVO> consultApplyVOS = consultApplyMapper.showApplyByStuName(stuName);
        return JsonResponse.success(consultApplyVOS,"success!");
    }


    /**
     * 描述：心理助理查看所有未完成的申请
     *
     */
    @Override
    public JsonResponse showUnfinishedApplies() {
        List<ConsultApplyVO> consultApplyVOS = consultApplyMapper.showUnfinishedApplies();
        return JsonResponse.success(consultApplyVOS,"success!");
    }


    /**
     * 描述：心理助理根据学生姓名查看未完成申请
     *
     */
    @Override
    public JsonResponse showUnfinishedApplyByStuName(String stuName) {
        List<ConsultApplyVO> consultApplyVOS = consultApplyMapper.showUnfinishedApplyByStuName(stuName);
        return JsonResponse.success(consultApplyVOS,"success!");
    }


    /**
     * 描述：心理助理处理咨询预约申请
     *
     */
    @Override
    public JsonResponse handleApply(Map form) {
        //该条申请记录ID
        Long caID = Long.parseLong(form.get("ca_id").toString());
        Long sID = Long.parseLong(form.get("s_id").toString());
        Integer tpID = (Integer)form.get("tp_id");
        Long lID = Long.parseLong(form.get("l_id").toString());
        Long cID = Long.parseLong(form.get("c_id").toString());
        LocalDate date = LocalDate.parse((String)form.get("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd"));

        //要插入的8条记录
        List<ConsultAppointmentRecord> c = new ArrayList<>(8);
        for(int i = 0;i < 8;i++){
            ConsultAppointmentRecord cc = new ConsultAppointmentRecord();
            cc.setSId(sID)
                    .setTpId(tpID)
                    .setLocationId(lID)
                    .setCId(cID)
                    .setDate(date.plusDays(7*i));
            c.add(cc);
        }

        //更新apply里的is_finished字段为1
        UpdateWrapper<ConsultApply> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(ConsultApply::getConsultApplyId,caID)
                .set(ConsultApply::getIsFinished,1);
        consultApplyMapper.update(null,wrapper);

        //更新咨询师排班里的free_time字段
        UpdateWrapper<ConsultantDuty> wrapper2 = new UpdateWrapper<>();
        wrapper2.lambda().eq(ConsultantDuty::getTpId,tpID)
                .eq(ConsultantDuty::getCId,cID)
                .set(ConsultantDuty::getFreeTime,date.plusDays(7*8));

        //插入records
        cars.saveBatch(c);
        return JsonResponse.successMessage("处理成功!");
    }


    /**
     * 描述：成功预约之后，发送短信
     *
     */
    @Override
    public void sendMessage() {

    }



}
