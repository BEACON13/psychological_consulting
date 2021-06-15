package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.service.ConsultAppointmentRecordService;
import com.example.mybatisplus.service.ConsultAppointmentReportService;
import com.example.mybatisplus.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ClosingReportService;
import com.example.mybatisplus.model.domain.ClosingReport;

import java.util.Map;


/**
 *
 *  前端控制器
 *
 *
 * @author Beacon
 * @since 2021-06-14
 * @version v1.0
 */
@Controller
@RequestMapping("/api")
public class ClosingReportController {

    private final Logger logger = LoggerFactory.getLogger( ClosingReportController.class );

    @Autowired
    private ClosingReportService closingReportService;

    @Autowired
    private ConsultAppointmentRecordService consultAppointmentRecordService;

    @Autowired
    private StudentService studentService;

    @Autowired
    private ConsultAppointmentReportService consultAppointmentReportService;

    /*
     *插入结案报告
     */
    @RequestMapping(value = "/consultant/insert/closingReport")
    @ResponseBody
    public JsonResponse insertClosingReport(@RequestParam("closingReport") Map<String,Object> info) throws Exception {

        Long sId = (Long) info.get("sId");

        //检查最后一次record
        if(!consultAppointmentReportService.checkLastRecordIsClosed(sId))
            return JsonResponse.failure("最后一次咨询未结案");

        //填写内容
        ClosingReport report = new ClosingReport();
        report.setCId((Long) info.get("cId"))
                .setConsultEffectSelf((String) info.get("consultEffectSelf"))
                .setProblemType((String) info.get("problemType"));

        //计算学生完成的咨询次数
        int num = consultAppointmentRecordService.countConsultingNum(sId);
        report.setConsultNum(num);

        closingReportService.save(report);

        //修改学生is_qualified字段
        studentService.setUnQualified(sId);

        //去除咨询record中没有finish的
        consultAppointmentRecordService.deleteUndoneRecords(sId);

        return JsonResponse.successMessage("插入完成");
    }


    /*
     * 检查是否允许插入结案报告
     * 检查方法是查看学生is_qualified字段是否为1
     * 因为插入结案报告时会将该字段置为0
     */
    @RequestMapping(value = "/consultant/insert/closingReport/allow")
    @ResponseBody
    public JsonResponse insertClosingReport(@RequestParam("student_id") Long id){
        return studentService.isQualified(id) ?
                JsonResponse.successMessage("请填写") :
                JsonResponse.failure("不满足填写要求");
    }

}

