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
    @RequestMapping(value = "/admin/alterConDuty", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse alterConsultantDuty(@RequestParam("tp_id")Integer tpID,@RequestParam("c_id")Long cID,@RequestParam("l_id")Long lID){
        return consultantDutyService.alterConsultantDuty(tpID,cID,lID);
    }


    /**
     * 描述：中心管理员删除咨询师排班
     *
     */
    @RequestMapping(value = "/admin/deleteConDuty", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse deleteConsultantDuty(@RequestParam("cd_id")Long cdID,@RequestParam("tp_id")Integer tpID,@RequestParam("c_id")Long cID){
        return consultantDutyService.deleteConsultantDuty(cdID,tpID,cID);
    }


    /**
     * 描述：中心管理员新增咨询师排班
     *
     */
    @RequestMapping(value = "/admin/insertConDuty", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse insertConsultantDuty(@RequestParam("tp_id")Integer tpID,@RequestParam("weekday")Integer weekday,@RequestParam("c_id")Long cID,@RequestParam("l_id")Long lID){
        return consultantDutyService.insertConsultantDuty(tpID,weekday,cID,lID);
    }
}

