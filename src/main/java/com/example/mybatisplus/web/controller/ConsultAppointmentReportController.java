package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.utls.SecurityUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ConsultAppointmentReportService;
import com.example.mybatisplus.model.domain.ConsultAppointmentReport;

import java.lang.reflect.Method;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
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
public class ConsultAppointmentReportController {

    private final Logger logger = LoggerFactory.getLogger( ConsultAppointmentReportController.class );

    @Autowired
    private ConsultAppointmentReportService consultAppointmentReportService;

    /*
     * 咨询师插入咨询报告
     */
    @RequestMapping(value = "/consultant/insert/consultReport")
    @ResponseBody
    public JsonResponse insertReport(@RequestBody Map<String,Object> info){
        ConsultAppointmentReport report = new ConsultAppointmentReport();
        report.setConsultAppointId(Long.parseLong(info.get("consult_appoint_id").toString()))
                .setSId((Long.parseLong (info.get("sId").toString())))
                .setTpId((int) info.get("tpId"))
                .setConsultResult((String) info.get("consultResult"))
                .setCId((Long.parseLong (info.get("cId").toString())));

        LocalDate date = LocalDate.parse((String) info.get("date")
                , DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        report.setDate(date);

        consultAppointmentReportService.insertReport(report);

        return JsonResponse.success("插入完成");
    }

    /*
     * 咨询师查看自己的所有咨询报告
     */
    @RequestMapping(value = "/consultant/show/report/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse consultantGetAllReport(){
        List reports =consultAppointmentReportService
                .getRecordByCon(SecurityUtils.getCurrentUserInfo().getPId());
        return JsonResponse.success(reports);
    }

    /*
     * 咨询师查看自己与某学生的咨询报告
     */
    @RequestMapping(value = "/consultant/show/report/stu", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse consultantGetReportByStu(@RequestParam("stuName") String stuName){
        List reports =consultAppointmentReportService
                .getRecordByConAndStu(SecurityUtils.getCurrentUserInfo().getPId(),stuName);
        return JsonResponse.success(reports);
    }

}

