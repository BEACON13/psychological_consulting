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

import java.util.Map;


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
@RequestMapping("/api")
public class FirstVisitReportController {

    private final Logger logger = LoggerFactory.getLogger( FirstVisitReportController.class );

    @Autowired
    private FirstVisitReportService firstVisitReportService;

    /**
     * 描述：初访员插入初访报告
     *
     */
    @RequestMapping(value = "/FirstVisitor/insertFVReport")
    @ResponseBody
    public JsonResponse insertFVReport(@RequestBody Map form){
        return firstVisitReportService.insertFVReport(form);
    }



    /**
     * 描述：该初访员查看所有报告
     *
     */
    @RequestMapping(value = "/FirstVisitor/showAllFVReports", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showAllFVReports(){
        return firstVisitReportService.showAllFVReports();
    }


    /**
     * 描述：该初访员根据学生姓名搜索是否有属于自己的初访报告
     *
     */
    @RequestMapping(value = "/FirstVisitor/searchFVReports", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showFVReportsByName(@RequestParam("stuName")String stuName){
        return firstVisitReportService.getFVReportsByName(stuName);
    }

    /**
     * 描述：初访员根据记录查看相关报告
     *
     */
    @RequestMapping(value = "/FirstVisitor/showFVReportByRecord", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showFVReportsByFvrId(@RequestParam("fvrId")Long fvrId){
        return firstVisitReportService.getFVReportsByFvrId(fvrId);
    }

    /**
     * 描述：学生根据记录查看相应的初访报告
     *
     */
    @RequestMapping(value="/student/showFVReports",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getFVRecord(@RequestParam("fvr_id")Long fvrID){
        return firstVisitReportService.getFVReportsByFvrId(fvrID);
    }
}

