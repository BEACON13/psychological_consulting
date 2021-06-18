package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.model.vo.FirstApplyVO;
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

import java.util.HashMap;
import java.util.List;
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

    /*
     * 描述：检查学生是否有预约申请的资格
     *
     */
    @RequestMapping(value = "/student/firstApply/qualification")
    @ResponseBody
    public JsonResponse checkStudentFirstApplyQualification(){

        Student student = SecurityUtils.getCurrentStudentInfo();

        return studentService.isAllowedFirstApply(student.getSId()) ?
                JsonResponse.successMessage("可以申请") :
                JsonResponse.failure("没有申请资格！");
    }

    /*
     * 描述：插入新预约申请
     *
     */
    @RequestMapping(value = "/student/insert/firstApply")
    @ResponseBody
    public JsonResponse insertApply(@RequestBody Map<String,Object> info) {

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
                .setConsultHistory((String) info.get("consultHistory"));


        if(firstApplyService.insertFirstApply(firstApply)>0)
            return JsonResponse.successMessage("插入成功");

        return JsonResponse.failure("插入失败");
    }

    /*
     * 管理员获得初访申请
     * 其中包括紧急申请和普通申请
     * 紧急申请得分大于75
     */
    @RequestMapping(value = "/admin/firstApply", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse adminGetFirstApply(){
        List<FirstApplyVO> urgentApplies = firstApplyService.getUrgentApply();
        List<FirstApplyVO> normalApplies = firstApplyService.getNormalApply();

        Map<String,Object> map = new HashMap<>();
        map.put("紧急申请",urgentApplies);
        map.put("普通申请",normalApplies);

        return JsonResponse.success(map);
    }


}

