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

import java.util.Map;


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
     * 描述：学生点击申请咨询，查看是否有资格
     *
     */
    @RequestMapping(value = "/student/isAllowedtoAC", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse isAllowed(){

        return consultApplyService.isAllowedtoApply();
    }

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


    /**
     * 描述：心理助理查看所有申请
     *
     */
    @RequestMapping(value = "/assistant/showAllApplies", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showAllApplies(){

        return consultApplyService.showAllApplies();
    }


    /**
     * 描述：心理助理根据学生姓名查看申请
     *
     */
    @RequestMapping(value = "/assistant/showApplyByStu", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showApplyByStuName(@RequestParam("stuName")String stuName){

        return consultApplyService.showApplyByStuName(stuName);
    }


    /**
     * 描述：心理助理查看所有未完成的申请
     *
     */
    @RequestMapping(value = "/assistant/showUnfinishedApplies", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showUnfinishedApplies(){

        return consultApplyService.showUnfinishedApplies();
    }


    /**
     * 描述：心理助理根据学生姓名查看未完成申请
     *
     */
    @RequestMapping(value = "/assistant/showUnfinishedApplyByStu", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse showUnfinishedApplyByStuName(@RequestParam("stuName")String stuName){

        return consultApplyService.showUnfinishedApplyByStuName(stuName);
    }


    /**
     * 描述：心理助理处理咨询预约申请
     *
     */
    @RequestMapping(value = "/assistant/handleApply")
    @ResponseBody
    public JsonResponse handleApply(@RequestBody Map form){

        return consultApplyService.handleApply(form);
    }
}

