package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.ConsultApplyService;
import com.example.mybatisplus.model.domain.ConsultApply;


/**
 *
 *  前端控制器
 *
 *
 * @author Beacon
 * @since 2021-06-14
 * @version v1.0
 */
@Controller
@RequestMapping("/api")
public class ConsultApplyController {

    private final Logger logger = LoggerFactory.getLogger( ConsultApplyController.class );

    @Autowired
    private ConsultApplyService consultApplyService;

    /**
     * 描述：学生申请咨询
     *
     */
    @RequestMapping(value = "/student/applyConsult", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse applyCon(@RequestParam("tp1")Integer tp1,@RequestParam(value="tp2")Integer tp2
            ,@RequestParam(value="tp3")Integer tp3){

        return consultApplyService.applyConsult(tp1,tp2,tp3);
    }
}
