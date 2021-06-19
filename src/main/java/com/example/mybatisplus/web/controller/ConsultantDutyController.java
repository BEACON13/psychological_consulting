package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ConsultantDutyService;
import com.example.mybatisplus.model.domain.ConsultantDuty;

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
@RequestMapping("/api/consultantDuty")
public class ConsultantDutyController {

    private final Logger logger = LoggerFactory.getLogger( ConsultantDutyController.class );

    @Autowired
    private ConsultantDutyService consultantDutyService;


    /**
     * 描述：显示该tp下，排班咨询师姓名及free_time
     *
     */
    @RequestMapping(value = "/assistant/showFreeTime", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showFreeTime(@RequestParam("tp_id")Integer tpID){

        return consultantDutyService.showFreeTime(tpID);
    }


    /**
     * 描述：中心管理员查看咨询师排班
     *
     */
    @RequestMapping(value = "/admin/showConDuty", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showConsultantDuty(){
        return consultantDutyService.showConsultantDuty();
    }


    /**
     * 描述：中心管理员更改咨询师排班地点
     *
     */
    @RequestMapping(value = "/admin/alterConDuty")
    @ResponseBody
    public JsonResponse alterConsultantDuty(@RequestBody Map form){
        Long cdID = Long.parseLong(form.get("cd_id").toString());
        Integer tpID = (Integer)form.get("tp_id");
        Long cID = Long.parseLong(form.get("c_id").toString());
        Long lID = Long.parseLong(form.get("l_id").toString());
        return consultantDutyService.alterConsultantDuty(cdID,tpID,cID,lID);
    }


    /**
     * 描述：中心管理员删除咨询师排班
     *
     */
    @RequestMapping(value = "/admin/deleteConDuty")
    @ResponseBody
    public JsonResponse deleteConsultantDuty(@RequestBody Map form){
        Long cdID = Long.parseLong(form.get("cd_id").toString());
        Integer tpID = (Integer)form.get("tp_id");
        Long cID = Long.parseLong(form.get("c_id").toString());
        return consultantDutyService.deleteConsultantDuty(cdID,tpID,cID);
    }


    /**
     * 描述：中心管理员新增咨询师排班
     *
     */
    @RequestMapping(value = "/admin/insertConDuty", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse insertConsultantDuty(@RequestBody Map form){
        Integer tpID = (Integer)form.get("tp_id");
        Integer weekday = (Integer)form.get("weekday");
        Long cID = Long.parseLong(form.get("c_id").toString());
        Long lID = Long.parseLong(form.get("l_id").toString());
        return consultantDutyService.insertConsultantDuty(tpID,weekday,cID,lID);
    }
}

