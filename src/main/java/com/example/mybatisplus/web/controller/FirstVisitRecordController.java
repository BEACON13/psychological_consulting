package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.FirstVisitRecordService;
import com.example.mybatisplus.model.domain.FirstVisitRecord;


/**
 *
 *  前端控制器
 *
 *
 * @author Kristy
 * @since 2021-06-13
 * @version v1.0
 */
@Controller
@RequestMapping("/api")
public class FirstVisitRecordController {

    private final Logger logger = LoggerFactory.getLogger( FirstVisitRecordController.class );

    @Autowired
    private FirstVisitRecordService firstVisitRecordService;

    /**
     * 描述：显示该初访员未完成的初访预约
     *
     */
    @RequestMapping(value="/firstVisit/showFVRecords",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse showFVRecords(){
        return firstVisitRecordService.showRecords();
    }

    /**
     * 描述：显示该初访员所有的初访记录
     *
     */
    @RequestMapping(value="/firstVisit/showAllFVRecords",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showAllFVRecords(){
        return firstVisitRecordService.showAllRecords();
    }

    /**
     * 描述：该初访员根据学生姓名搜索是否有属于自己的初访预约记录
     *
     */
    @RequestMapping(value="/firstVisit/searchFVRecords",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showFVRecordsByName(@RequestParam("stuName") String stuName){
        return firstVisitRecordService.getRecordsByName(stuName);
    }


    /**
     * 描述：学生查看初访预约记录
     *
     */
    @RequestMapping(value="/student/showFVRecords",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getFVRecord(){
        return firstVisitRecordService.getFVRecord();
    }


    /**
     * 描述：学生提前一天取消初访预约
     *
     */
    @RequestMapping(value="/student/manageFVRecord",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse manageFVRecord(@RequestParam("fvr_id")Long fvrId){
        return firstVisitRecordService.manageFVRecord(fvrId);
    }

    /*
     * 中心管理员查看所有的预约记录
     */
    @RequestMapping(value="/admin/show/FVRecords",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse adminShowAllFirstVisitRecords(){
        return JsonResponse.success(firstVisitRecordService.getAllRecordsAdmin());
    }
}

