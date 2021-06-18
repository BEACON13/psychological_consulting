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
import org.springframework.web.context.request.async.TimeoutCallableProcessingInterceptor;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;


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

    /*
     * 中心管理员查看根据咨询师姓名查看预约记录
     */
    @RequestMapping(value="/admin/show/FVRecords/firstVisitor",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showFirstVisitRecordsByFirstVisitor(@RequestParam("fv_name")String name){
        return JsonResponse.success(firstVisitRecordService.getRecordByFirstVisitorName(name));
    }

    /*
     * 中心管理员查看根据咨询师姓名查看预约记录
     */
    @RequestMapping(value="/admin/show/FVRecords/firstVisitor/unfinished",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showFirstVisitRecordsByFirstVisitorUnfinished(@RequestParam("fv_name")String name){
        return JsonResponse.success(firstVisitRecordService.getRecordByFirstVisitorNameUnfinished(name));
    }

    /*
     * 中心管理员修改预约记录
     * info中只需要给出被修改信息和record的id
     */
    @RequestMapping(value="/admin/change/FVRecords")
    @ResponseBody
    public JsonResponse adminChangeFVRecords(@RequestBody Map<String,Object> info){
        FirstVisitRecord record = new FirstVisitRecord();
        record.setFvrId(Long.parseLong(info.get("fvrId").toString()))
                .setSId(Long.parseLong(info.get("sId").toString()))
                .setTpId((Integer) info.get("tpId"))
                .setLocationId(Long.parseLong(info.get("locationId").toString()))
                .setFvId(Long.parseLong(info.get("fvId").toString()))
                .setDate(LocalDate.parse((String)info.get("date"), DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return firstVisitRecordService.updateById(record)?
                JsonResponse.successMessage("修改成功"):
                JsonResponse.failure("修改出错");
    }


    /*
     * 中心管理员审核初访申请后，进行插入操作
     */
    @RequestMapping(value="/admin/insert/FVRecord")
    @ResponseBody
    public JsonResponse insertFVRecord(@RequestBody Map<String,Object> info){
        FirstVisitRecord record = new FirstVisitRecord();
        record.setSId(Long.parseLong(info.get("sId").toString()))
                .setTpId((Integer) info.get("tpId"))
                .setLocationId(Long.parseLong(info.get("locationId").toString()))
                .setFvId(Long.parseLong(info.get("fvId").toString()))
                .setDate(LocalDate.parse((String)info.get("date"),
                        DateTimeFormatter.ofPattern("yyyy-MM-dd")));

        return firstVisitRecordService.insertFVRecord(record)>0?
                JsonResponse.successMessage("插入完成"):
                JsonResponse.failure("插入出错");
    }
}

