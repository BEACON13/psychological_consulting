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
@RequestMapping("/api/firstVisitorDuty")
public class FirstVisitorDutyController {

    private final Logger logger = LoggerFactory.getLogger( FirstVisitorDutyController.class );

    @Autowired
    private FirstVisitorDutyService firstVisitorDutyService;


}
