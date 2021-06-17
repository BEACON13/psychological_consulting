package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.model.domain.Person;
import com.example.mybatisplus.model.domain.Student;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ConsultAppointmentRecordService;

import java.util.List;
import java.util.Map;


/**
 *
 *  前端控制器
 *
 *
 * @author Beacon
 * @since 2021-06-13
 * @version v1.0
 */
@Controller
@RequestMapping("/api")
public class ConsultAppointmentRecordController {

    private final Logger logger = LoggerFactory.getLogger( ConsultAppointmentRecordController.class );

    @Autowired
    private ConsultAppointmentRecordService consultAppointmentRecordService;


    /*
     * 描述：获得某咨询师的所有记录
     *
     */
    @RequestMapping(value = "/consultant/appointRecord/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getAllRecord(){
        Person consultant = SecurityUtils.getCurrentUserInfo();
        List records = consultAppointmentRecordService
                .getAllRecordByConsultantID(consultant.getPId());
        return JsonResponse.success(records);
    }

    /*
     * 描述：查看某心理咨询师已进行但未填写的所有咨询
     * 即已经进行了咨询活动但是没有填写咨询报告的咨询记录
     */
    @RequestMapping(value = "/consultant/appointRecord/notfilled", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getRecordNotFilledIn(){
        Person consultant = SecurityUtils.getCurrentUserInfo();
        List records = consultAppointmentRecordService
                .getRecordNotFilledInByConsultantID(consultant.getPId());
        return JsonResponse.success(records);
    }

    /*
     * 描述：查看某心理咨询师未完成的咨询
     * 不论日期
     */
    @RequestMapping(value = "/consultant/appointRecord/unfinished", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getUnfinishedRecord(){
        Person consultant = SecurityUtils.getCurrentUserInfo();
        List records = consultAppointmentRecordService
                .getAllUnfinishedRecordByConsultantID(consultant.getPId());
        return JsonResponse.success(records);
    }


    /*
     * ！！！！！学生id可能拿不到！！！！！
     * 描述：查看某心理咨询师与某学生共同参与的咨询记录
     * 通过ID查询
     * 不论日期
     */
    @RequestMapping(value = "/consultant/appointRecord/constuid", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getUnfinishedRecordByStuID(@RequestParam("studentId") Long sId){
        Person consultant = SecurityUtils.getCurrentUserInfo();
        List records = consultAppointmentRecordService
                .getRecordByConsultantAndStudentID(consultant.getPId(),sId);
        return JsonResponse.success(records);
    }


    /*
     * 描述：查看某心理咨询师与某学生共同参与的咨询记录
     * 通过咨询师ID和学生姓名查询
     * 不论日期
     */
    @RequestMapping(value = "/consultant/appointRecord/constu", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getUnfinishedRecord(@RequestParam("studentName") String stuName){
        Person consultant = SecurityUtils.getCurrentUserInfo();
        List records = consultAppointmentRecordService
                .getRecordByConsultantAndStudent(consultant.getPId(),stuName);
        return JsonResponse.success(records);
    }


    /*
     * 描述：获取未完成的和未填写的
     * 以map封装
     */
    @RequestMapping(value = "/consultant/appointRecord/undone", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getUnfinishedAndNotFilled(){
        Person consultant = SecurityUtils.getCurrentUserInfo();
        Map map = consultAppointmentRecordService
                .getUnfinishedAndNotFilledIn(consultant.getPId());
        return JsonResponse.success(map);
    }

    /*
     * 描述：学生查看自己的咨询记录
     * 包括进行和未进行的
     */
    @RequestMapping(value = "/student/appointRecord/show", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getStuRecord(){
        Student stu = SecurityUtils.getCurrentStudentInfo();
        List records = consultAppointmentRecordService.getStuRecord(stu.getSId());
        return JsonResponse.success(records);
    }


    /**
     * 描述：心理助理查看所有预约记录（包括各咨询师、各学生）
     *
     */
    @RequestMapping(value = "/assistant/showAll", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showAllRecords(){
        return consultAppointmentRecordService.showAllRecords();
    }


    /**
     * 描述：心理助理根据咨询师姓名查看咨询预约记录
     *
     */
    @RequestMapping(value = "/assistant/showByCon", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showRecordsByConName(@RequestParam("ConName")String ConName){
        return consultAppointmentRecordService.showRecordsByConName(ConName);
    }


    /**
     * 描述：心理助理根据学生姓名查看咨询预约记录
     *
     */
    @RequestMapping(value = "/assistant/showByStu", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showRecordsByStuName(@RequestParam("StuName")String StuName){
        return consultAppointmentRecordService.showRecordsByStuName(StuName);
    }


    /**
     * 描述：心理助理查看所有未完成的预约记录（包括各咨询师、各学生）
     *
     */
    @RequestMapping(value = "/assistant/showUnfinished", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showUnfinishedRecords(){
        return consultAppointmentRecordService.showUnfinishedRecords();
    }


    /**
     * 描述：心理助理根据咨询师的姓名查看所有未完成的预约记录
     *
     */
    @RequestMapping(value = "/assistant/showUnfinishedByCon", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showUnfinishedRecordsByConName(@RequestParam("ConName")String ConName){
        return consultAppointmentRecordService.showUnfinishedRecordsByConName(ConName);
    }


    /**
     * 描述：心理助理根据学生的姓名查看所有未完成的预约记录
     *
     */
    @RequestMapping(value = "/assistant/showUnfinishedByStu", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showUnfinishedRecordsByStuName(@RequestParam("StuName")String StuName){
        return consultAppointmentRecordService.showUnfinishedRecordsByStuName(StuName);
    }


    /**
     * 描述：心理助理修改某条预约记录
     *
     */
    @RequestMapping(value = "/assistant/manageRecords")
    @ResponseBody
    public JsonResponse manageRecords(@RequestBody Map form){
        return consultAppointmentRecordService.manageRecords(form);
    }



}

