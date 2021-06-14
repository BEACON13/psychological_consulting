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
@RequestMapping("/api/consultApply")
public class ConsultApplyController {

    private final Logger logger = LoggerFactory.getLogger( ConsultApplyController.class );

    @Autowired
    private ConsultApplyService consultApplyService;

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        ConsultApply  consultApply =  consultApplyService.getById(id);
        return JsonResponse.success(consultApply);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        consultApplyService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateConsultApply(@PathVariable("id") Long  id,ConsultApply  consultApply) throws Exception {
        consultApply.setConsultApplyId(id);
        consultApplyService.updateById(consultApply);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建ConsultApply
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(ConsultApply  consultApply) throws Exception {
        consultApplyService.save(consultApply);
        return JsonResponse.success(null);
    }
}

