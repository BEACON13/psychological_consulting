package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.service.StudentService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.FirstApplyService;
import com.example.mybatisplus.model.domain.FirstApply;

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
public class FirstApplyController {

    private final Logger logger = LoggerFactory.getLogger( FirstApplyController.class );

    @Autowired
    private FirstApplyService firstApplyService;

    @Autowired
    private StudentService studentService;

    /**
     * 描述：插入新预约申请
     *
     */
    @RequestMapping(value = "/student/insert/firstApply")
    @ResponseBody
    public JsonResponse insertApply(@RequestParam("firstApply") Map<String,Object> info) {

        Student student = SecurityUtils.getCurrentStudentInfo();
        if (studentService.isAllowedFirstApply(student.getSId())){
            JsonResponse.failure("您没有资格预约初访");
        }

        FirstApply firstApply=new FirstApply();
        firstApply.setSId(Long.parseLong (info.get("sId").toString()))
                .setTpId((Integer) info.get("tpID"))
                .setScore((Integer) info.get("score"))
                .setName((String) info.get("name"))
                .setPhone((String) info.get("phone"))
                .setAddress((String) info.get("address"))
                .setEmergencyPhone((String) info.get("emergencyPhone"))
                .setPhysicalIllness((String) info.get("physicalIllness"))
                .setIsDiagnosed((Boolean) info.get("isDiagnosed"))
                .setEmergencyLevel((String) info.get("emergencyLevel"))
                .setProblemType((String) info.get("problemType"))
                .setConsultExpectation((String) info.get("consultExpectation"))
                .setConsultHistory((String) info.get("consultHistory"))
                .setIsFinished((Boolean) info.get("isFinished"));

        if(firstApplyService.insertFirstApply(firstApply)>0)
            return JsonResponse.success(null);

        return JsonResponse.failure("插入失败");
    }


}

