package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.domain.EvaluationQuestion;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Beacon
 * @since 2022-04-04
 */
public interface EvaluationQuestionService extends IService<EvaluationQuestion> {

    /**
     * 根据问卷ID查询评估问题
     *
     * @param evaluationId 问卷id
     * @return 评估问题
     */
    List<String> listQuestions(Long evaluationId);

}
