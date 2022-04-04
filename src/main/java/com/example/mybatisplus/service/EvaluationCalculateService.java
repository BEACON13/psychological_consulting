package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.EvaluationResult;

import java.util.List;

public interface EvaluationCalculateService {

    /**
     * 计算评估结果
     *
     * @param answers 学生问卷答案
     * @return 评估结果
     */
    EvaluationResult calculate(List<Integer> answers);
}
