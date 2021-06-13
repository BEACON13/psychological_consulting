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
    @RequestMapping(value="/firstVisit",method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showFVRecord(){
        return firstVisitRecordService.showRecord();
    }
}

