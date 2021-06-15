package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.model.domain.ConsultApply;
import com.example.mybatisplus.mapper.ConsultApplyMapper;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.example.mybatisplus.model.domain.FirstApply;
import com.example.mybatisplus.model.domain.FirstVisitReport;
import com.example.mybatisplus.service.*;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

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
    public boolean isAllowedtoApply(Long id) {

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


        return flag1&&flag2&&flag3;
    }

    /**
     * 描述：学生填写咨询申请
     *
     */
    @Override
    public JsonResponse applyConsult(Integer tp1, Integer tp2, Integer tp3) {
        Long id = SecurityUtils.getCurrentStudentInfo().getSId();
        if(!isAllowedtoApply(id))
            return JsonResponse.failure("没有申请资格！");


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
}
