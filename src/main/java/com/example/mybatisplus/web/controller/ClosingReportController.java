package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.service.ConsultAppointmentRecordService;
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
    /*
    插入
     */
    @RequestMapping(value = "/consultant/insert/closingReport", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse insertClosingReport(@RequestParam("closingReport") Map<String,Object> info) throws Exception {
        ClosingReport report = new ClosingReport();
        report.setCId((Long) info.get("cId"))
                .setConsultEffectSelf((String) info.get("consultEffectSelf"))
                .setProblemType((String) info.get("problemType"))
                .setSId((Long) info.get("sId"));

        int num = consultAppointmentRecordService
                .countConsultingNum((Long) info.get("sId"));
        report.setConsultNum(num);

        closingReportService.save(report);
        return JsonResponse.successMessage("插入完成");
    }

}

