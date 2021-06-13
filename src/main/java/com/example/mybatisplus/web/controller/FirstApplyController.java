package com.example.mybatisplus.web.controller;

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
 * @since 2021-06-13
 * @version v1.0
 */
@Controller
@RequestMapping("/api/firstApply")
public class FirstApplyController {

    private final Logger logger = LoggerFactory.getLogger( FirstApplyController.class );

    @Autowired
    private FirstApplyService firstApplyService;

    /**
     * 描述：插入新预约申请
     *
     */
    @RequestMapping(value = "/insert", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse insertApply(@RequestParam("firstApply") Map<String,Object> info)throws Exception {
        FirstApply firstApply=new FirstApply();
        firstApply.setSId((Long) info.get("sId"))
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
        if(firstApplyService.insertFirstApply(firstApply))
            return JsonResponse.success(null);
        return JsonResponse.failure("插入失败");
    }

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        FirstApply  firstApply =  firstApplyService.getById(id);
        return JsonResponse.success(firstApply);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        firstApplyService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateFirstApply(@PathVariable("id") Long  id,FirstApply  firstApply) throws Exception {
        firstApply.setFaId(id);
        firstApplyService.updateById(firstApply);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建FirstApply
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(FirstApply  firstApply) throws Exception {
        firstApplyService.save(firstApply);
        return JsonResponse.success(null);
    }
}

