package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.AddConsultService;
import com.example.mybatisplus.model.domain.AddConsult;

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

    /**
     * 描述：咨询师申请追加时间段
     *
     */
    @RequestMapping(value = "/consultant/applyTP")
    @ResponseBody
    public JsonResponse addConsultTP(@RequestBody Map form){

        return addConsultService.addConsultTP(form);
    }
}

