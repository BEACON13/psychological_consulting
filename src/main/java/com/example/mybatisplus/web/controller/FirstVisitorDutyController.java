package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.FirstVisitorDutyService;
import com.example.mybatisplus.model.domain.FirstVisitorDuty;


/**
 *
 *  前端控制器
 *
 *
 * @author Kristy
 * @since 2021-06-15
 * @version v1.0
 */
@Controller
@RequestMapping("/api")
public class FirstVisitorDutyController {

    private final Logger logger = LoggerFactory.getLogger( FirstVisitorDutyController.class );

    @Autowired
    private FirstVisitorDutyService firstVisitorDutyService;

    /*
     * 描述：显示初访员空闲排班
     *
     */
    @RequestMapping(value="/admin/getFVDuty/available",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse getAvailableFVDuty(){
        return JsonResponse.success(firstVisitorDutyService.getAvailableFVDuty());
    }


    /*
     * 描述：显示初访员所有排班
     *
     */
    @RequestMapping(value="/admin/getFVDuty/all",method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse getAllFVDuty(){
        return JsonResponse.success(firstVisitorDutyService.getAllFVDuty());
    }


    /**
     * 描述：中心管理员修改初访员排班地点
     *
     */
    @RequestMapping(value = "/admin/alterFVDuty", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse alterFVDuty(@RequestParam("fvd_id")Long fvdID,@RequestParam("tp_id")Integer tpID,
                                    @RequestParam("fv_id")Long fvID,@RequestParam("l_id")Long lID){
        return firstVisitorDutyService.alterFVDuty(fvdID,tpID,fvID,lID);
    }


    /**
     * 描述：中心管理员删除初访员排班
     *
     */
    @RequestMapping(value = "/admin/deleteFVDuty", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse deleteFVDuty(@RequestParam("fvd_id")Long fvdID,@RequestParam("tp_id")Integer tpID,
                                             @RequestParam("fv_id")Long fvID,@RequestParam("l_id")Long lID){
        return firstVisitorDutyService.deleteFVDuty(fvdID,tpID,fvID,lID);
    }


    /**
     * 描述：中心管理员新增初访员排班
     *
     */
    @RequestMapping(value = "/admin/insertFVDuty", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse insertFVDuty(@RequestParam("tp_id")Integer tpID,@RequestParam("fv_id")Long fvID,@RequestParam("l_id")Long lID){
        return firstVisitorDutyService.insertFVDuty(tpID,fvID,lID);
    }
}

