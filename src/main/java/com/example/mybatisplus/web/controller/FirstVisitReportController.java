package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.FirstVisitReportService;
import com.example.mybatisplus.model.domain.FirstVisitReport;


/**
 *
 *  前端控制器
 *
 *
 * @author Kristy
 * @since 2021-06-14
 * @version v1.0
 */
@Controller
@RequestMapping("/api/firstVisitReport")
public class FirstVisitReportController {

    private final Logger logger = LoggerFactory.getLogger( FirstVisitReportController.class );

    @Autowired
    private FirstVisitReportService firstVisitReportService;

    /**
     * 描述：初访员插入初访报告
     *
     */
    @RequestMapping(value = "/FirstVisitor/insertReport", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse insertReport(@RequestBody FirstVisitReport firstVisitReport){
        return firstVisitReportService.insertFVReport(firstVisitReport);
    }




    /**
     * 描述：初访员界面显示未完成报告
     *
     */
}

