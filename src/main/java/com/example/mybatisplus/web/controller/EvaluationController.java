package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.model.domain.EvaluationResult;
import com.example.mybatisplus.model.domain.EvaluationTable;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.model.vo.EvaluationResultVO;
import com.example.mybatisplus.service.EvaluationQuestionService;
import com.example.mybatisplus.service.EvaluationResultService;
import com.example.mybatisplus.service.EvaluationTableService;
import com.example.mybatisplus.service.param.EvaluationTableParam;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
@RequestMapping("/api/evaluation")
public class EvaluationController {

    private final Logger logger = LoggerFactory.getLogger(EvaluationController.class);

    @Resource
    private EvaluationTableService evaluationTableService;

    @Resource
    private EvaluationQuestionService evaluationQuestionService;

    @Resource
    private EvaluationResultService evaluationResultService;

    /**
     * 列出所有评测表
     */
    @RequestMapping(value = "/listAllEvaluations", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse listAllEvaluations() throws Exception {
        List<EvaluationTable> tableList = evaluationTableService.listAllEvaluations();
        return JsonResponse.success(tableList);
    }

    /**
     * 列出评测表内容
     */
    @RequestMapping(value = "/listQuestions", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse listQuestions(@RequestParam("id") Long id) throws Exception {
        List<String> evaluationQuestions = evaluationQuestionService.listQuestions(id);
        return JsonResponse.success(evaluationQuestions);
    }

    /**
     * 评测结果
     */
    @RequestMapping(value = "/evaluate", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse evaluate(@RequestBody EvaluationTableParam param) throws Exception {
        Student student = SecurityUtils.getCurrentStudentInfo();
        param.setStuId(student.getSId());
        EvaluationResult evaluationResults = evaluationTableService.evaluate(param);
        if (evaluationResults == null) {
            return JsonResponse.failure("评测失败");
        } else {
            return JsonResponse.success(evaluationResults);
        }
    }

    /**
     * 列出某学生的评测结果
     */
    @RequestMapping(value = "/listEvaluationResult", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse listEvaluationResult() throws Exception {
        Student student = SecurityUtils.getCurrentStudentInfo();
        List<EvaluationResultVO> evaluationResults = evaluationResultService.listEvaluationResult(student.getSId());
        return JsonResponse.success(evaluationResults);
    }

    @RequestMapping(value = "/listOneEvaluationResult", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse listOneEvaluationResult(@RequestParam("id") Long evaluationId) throws Exception {
        Student student = SecurityUtils.getCurrentStudentInfo();
        EvaluationResultVO evaluationResult = evaluationResultService.getOneEvaluationResult(student.getSId(), evaluationId);
        return JsonResponse.success(evaluationResult);
    }

}

