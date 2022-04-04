package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.EvaluationTableService;
import com.example.mybatisplus.model.domain.EvaluationTable;

import javax.annotation.Resource;
import java.util.List;


/**
 * 前端控制器
 *
 * @author Beacon
 * @version v1.0
 * @since 2022-04-04
 */
@Controller
@RequestMapping("/api/evaluationTable")
public class EvaluationTableController {

    private final Logger logger = LoggerFactory.getLogger(EvaluationTableController.class);

    @Resource
    private EvaluationTableService evaluationTableService;


    /**
     * 描述：根据Id 查询
     */
    @RequestMapping(value = "/listAllEvaluations", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse listAllEvaluations() throws Exception {
        List<EvaluationTable> tableList = evaluationTableService.listAllEvaluations();
        return JsonResponse.success(tableList);
    }

//    /**
//     * 描述：根据Id 查询
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
//    @ResponseBody
//    public JsonResponse getById(@PathVariable("id") Long id) throws Exception {
//        EvaluationTable evaluationTable = evaluationTableService.getById(id);
//        return JsonResponse.success(evaluationTable);
//    }
//
//    /**
//     * 描述：根据Id删除
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
//    @ResponseBody
//    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
//        evaluationTableService.removeById(id);
//        return JsonResponse.success(null);
//    }
//
//
//    /**
//     * 描述：根据Id 更新
//     */
//    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
//    @ResponseBody
//    public JsonResponse updateEvaluationTable(@PathVariable("id") Long id, EvaluationTable evaluationTable) throws Exception {
//        evaluationTable.setId(id);
//        evaluationTableService.updateById(evaluationTable);
//        return JsonResponse.success(null);
//    }

}

