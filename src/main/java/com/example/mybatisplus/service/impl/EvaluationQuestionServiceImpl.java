package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.mapper.EvaluationQuestionMapper;
import com.example.mybatisplus.model.domain.EvaluationQuestion;
import com.example.mybatisplus.service.EvaluationQuestionService;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Beacon
 * @since 2022-04-04
 */
@Service
public class EvaluationQuestionServiceImpl extends ServiceImpl<EvaluationQuestionMapper, EvaluationQuestion> implements EvaluationQuestionService {

    @Override
    public List<String> listQuestions(Long evaluationId) {
        List<EvaluationQuestion> evaluationQuestions = this.getBaseMapper().listQuestions(evaluationId);
        return evaluationQuestions.stream().map(EvaluationQuestion::getQuestion).collect(ArrayList::new, ArrayList::add, ArrayList::addAll);
    }
}
