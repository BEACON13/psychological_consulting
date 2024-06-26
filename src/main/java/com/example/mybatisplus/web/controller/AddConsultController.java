package com.example.mybatisplus.web.controller;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.example.mybatisplus.service.ConsultAppointmentRecordService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.AddConsultService;
import com.example.mybatisplus.model.domain.AddConsult;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Map;


/**
 *
 *  前端控制器
 *
 *
 * @author Kristy
 * @since 2021-06-17
 * @version v1.0
 */
@Controller
@RequestMapping("/api/addConsult")
public class AddConsultController {

    private final Logger logger = LoggerFactory.getLogger( AddConsultController.class );

    @Autowired
    private AddConsultService addConsultService;

    @Autowired
    private ConsultAppointmentRecordService consultAppointmentRecordService;

    /**
     * 描述：咨询师申请追加时间段
     *
     */
    @RequestMapping(value = "/consultant/applyTP")
    @ResponseBody
    public JsonResponse addConsultTP(@RequestBody Map form){

        return addConsultService.addConsultTP(form);
    }

    /*
     * 中心管理员查看所有追加申请
     */
    @RequestMapping(value = "/admin/showAddApply/all", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showAllAddApply(){
        return JsonResponse.success(addConsultService.getAllAddConsult());
    }

    /*
     * 中心管理员查看未完成的追加申请
     */
    @RequestMapping(value = "/admin/showAddApply/unfinished", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showUnfinishedAddApply(){
        return JsonResponse.success(addConsultService.getUnfinishedAddConsult());
    }

    /*
     * 中心管理员审核通过追加申请
     * 将其插入到咨询预约记录(ConsultAppointmentRecord)
     */
    @RequestMapping(value = "/admin/insert/apply")
    @ResponseBody
    public JsonResponse insertConsultApply(@RequestBody Map<String,Object> info){
        consultAppointmentRecordService.insertRecords(info);
        return JsonResponse.successMessage("插入成功");
    }

    /*
     * 中心管理员拒绝追加申请
     */
    @RequestMapping(value = "/admin/reject/apply", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse rejectConsultApply(@RequestParam("add_consult_id") Long id){
        return addConsultService.finishAdd(id)>0 ?
                JsonResponse.successMessage("拒绝成功"):
                JsonResponse.failure("拒绝出错");
    }
}

