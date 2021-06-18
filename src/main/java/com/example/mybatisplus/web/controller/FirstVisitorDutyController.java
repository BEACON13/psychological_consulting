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

}

