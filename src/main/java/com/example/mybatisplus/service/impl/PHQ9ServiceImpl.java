package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.common.consts.EvaluationTableEnum;
import com.example.mybatisplus.model.domain.EvaluationResult;
import com.example.mybatisplus.service.EvaluationCalculateService;

import java.util.List;

public class PHQ9ServiceImpl implements EvaluationCalculateService {

    private static final int PHQ9_ANSWER_NUM = 9;

    //小于此值 无抑郁症
    private static final int L1 = 5;

    //小于此值 可能有轻微抑郁症
    private static final int L2 = 10;

    //小于此值 可能有中度抑郁症
    private static final int L3 = 15;

    //小于此值 可能有重度抑郁症
    private static final int L4 = 20;

    @Override
    public EvaluationResult calculate(List<Integer> answers) {
        if (answers.size() != PHQ9_ANSWER_NUM) {
            throw new IllegalArgumentException("问卷答案错误，应当有9个答案");
        }
        EvaluationResult result = new EvaluationResult();
        result.setEvaluationTableId(EvaluationTableEnum.PHQ_9.getCode());

        int score = answers.stream().mapToInt(Integer::intValue).sum();
        result.setScore(score);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("您的PHQ-9问卷得分为：").append(score).append("分\n");
        if (score < L1) {
            stringBuilder.append("您没有抑郁症，请继续保持良好的生活习惯！");
        } else if (score < L2) {
            stringBuilder.append("您可能有轻微抑郁症，建议咨询心理医生或者医学工作者");
        } else if (score < L3) {
            stringBuilder.append("您可能有中度抑郁症，建议咨询心理医生或者医学工作者");
        } else if (score < L4) {
            stringBuilder.append("您可能有中重度抑郁症，建议咨询心理医生或者精神科医生");
        } else {
            stringBuilder.append("您可能有极重度抑郁症，务必咨询心理医生或者精神科医生");
        }

        if (score >= L1) {
            if (answers.get(0) > 1 || answers.get(3) > 1) {
                stringBuilder.append("\n您的PHQ-9问卷中，第1题和第4题的答案偏高，这是抑郁症的核心症状");
            }
            if (answers.get(8) > 0) {
                stringBuilder.append("\n您的PHQ-9问卷中，第9题的答案偏高，您具有自伤倾向");
            }
        }
        result.setInfo(stringBuilder.toString());
        return result;
    }
}
