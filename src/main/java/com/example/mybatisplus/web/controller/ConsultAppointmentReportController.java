package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ConsultAppointmentReportService;
import com.example.mybatisplus.model.domain.ConsultAppointmentReport;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

    @RequestMapping(value = "/consultant/insert/consultReport", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse insertReport(@RequestParam("consultReport") Map<String,Object> info){
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
}

