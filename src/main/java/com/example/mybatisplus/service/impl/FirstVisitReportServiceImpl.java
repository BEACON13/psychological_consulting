package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.mapper.FirstVisitRecordMapper;
import com.example.mybatisplus.mapper.StudentMapper;
import com.example.mybatisplus.model.domain.FirstVisitRecord;
import com.example.mybatisplus.model.domain.FirstVisitReport;
import com.example.mybatisplus.mapper.FirstVisitReportMapper;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.model.vo.FirstVisitReportVO;
import com.example.mybatisplus.service.FirstVisitReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-14
 */
@Service
public class FirstVisitReportServiceImpl extends ServiceImpl<FirstVisitReportMapper, FirstVisitReport> implements FirstVisitReportService {
    @Autowired
    private FirstVisitReportMapper firstVisitReportMapper;
    @Autowired
    private FirstVisitRecordMapper firstVisitRecordMapper;
    @Autowired
    StudentMapper studentMapper;

    /**
     * 描述：初访员插入初访报告
     *
     */
    @Override
    public JsonResponse insertFVReport(Map form) {
        //插入初访报告
        FirstVisitReport firstVisitReport = new FirstVisitReport();
        firstVisitReport.setFvrId(Long.parseLong(form.get("fvr_id").toString()))
                .setSId(Long.parseLong(form.get("s_id").toString()))
                .setTpId((Integer) form.get("tp_id"))
                .setFvId(Long.parseLong(form.get("fv_id").toString()))
                .setDangerLevel((String)form.get("danger_level"))
                .setProblemType((String)form.get("problem_type"))
                .setConclusion((String)form.get("conclusion"))
                .setDate(LocalDate.parse((String)form.get("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        firstVisitReportMapper.insert(firstVisitReport);

        //更新初访预约is_finished字段
        UpdateWrapper<FirstVisitRecord> wrapper = new UpdateWrapper<>();
        wrapper.lambda().set(FirstVisitRecord::getIsFinished,1).eq(FirstVisitRecord::getSId,firstVisitReport.getSId());
        firstVisitRecordMapper.update(null,wrapper);

        //更新学生是否拥有咨询资格字段
        if(firstVisitReport.getConclusion().equals("安排咨询")){
            UpdateWrapper<Student> wrapper2 = new UpdateWrapper<>();
            wrapper2.lambda().set(Student::getIsQualified,1).eq(Student::getSId,firstVisitReport.getSId());
            studentMapper.update(null,wrapper2);
        }

        return JsonResponse.successMessage("提交成功");
    }

    /**
     * 描述：该初访员查看所有报告
     *
     */
    @Override
    public JsonResponse showAllFVReports() {
        Long id = SecurityUtils.getUserInfo().getId();
        List<FirstVisitReportVO> firstVisitReportVOS = firstVisitReportMapper.showAllFVReports(id);

        return JsonResponse.success(firstVisitReportVOS,"success!");
    }


    /**
     * 描述：该初访员根据学生姓名搜索是否有属于自己的初访报告
     *
     */
    @Override
    public JsonResponse getFVReportsByName(String stuName) {
        Long id = SecurityUtils.getUserInfo().getId();
        List<FirstVisitReportVO> firstVisitReportVOS = firstVisitReportMapper.getFVReportsByName(id, stuName);
        return JsonResponse.success(firstVisitReportVOS,"success!");
    }




    /**
     * 描述：根据学生id搜索初访报告
     *
     */
    @Override
    public List<FirstVisitReport> getFVReportsBySId(Long SID) {
        QueryWrapper<FirstVisitReport> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FirstVisitReport::getSId,SID);
        List<FirstVisitReport> firstVisitReports = firstVisitReportMapper.selectList(wrapper);
        return firstVisitReports;
    }


    /**
     * 描述：初访员根据记录查看相关报告
     * 描述：学生查根据记录查看对应的初访报告
     *
     */
    @Override
    public JsonResponse getFVReportsByFvrId(Long fvrId) {
        FirstVisitReportVO fvReportByFvrId = firstVisitReportMapper.getFVReportByFvrId(fvrId);

        return JsonResponse.success(fvReportByFvrId,"success");
    }


}
