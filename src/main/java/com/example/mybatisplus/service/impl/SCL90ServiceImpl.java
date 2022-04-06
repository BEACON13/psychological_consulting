package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.common.consts.EvaluationTableEnum;
import com.example.mybatisplus.model.bo.SCL90FactorResult;
import com.example.mybatisplus.model.domain.EvaluationResult;
import com.example.mybatisplus.service.EvaluationCalculateService;

import java.util.ArrayList;
import java.util.List;

public class SCL90ServiceImpl implements EvaluationCalculateService {
    private static final int ANSWER_NUM = 90;
    /**
     * 躯体化
     */
    private int[] f1 = {1, 4, 12, 27, 40, 42, 48, 49, 52, 53, 56, 58};
    /**
     * 强迫症状
     */
    private int[] f2 = {3, 9, 10, 28, 38, 45, 46, 51, 55, 65};
    /**
     * 人际关系敏感
     */
    private int[] f3 = {6, 21, 34, 36, 37, 41, 61, 69, 73};
    /**
     * 抑郁
     */
    private int[] f4 = {5, 14, 15, 20, 22, 26, 29, 30, 31, 32, 54, 71, 79};
    /**
     * 焦虑
     */
    private int[] f5 = {2, 17, 23, 33, 39, 57, 72, 78, 80, 86};
    /**
     * 敌对
     */
    private int[] f6 = {11, 24, 63, 67, 74, 81};
    /**
     * 恐怖
     */
    private int[] f7 = {13, 25, 47, 50, 70, 75, 82};
    /**
     * 偏执
     */
    private int[] f8 = {8, 18, 43, 68, 76, 83};
    /**
     * 精神病性症状
     */
    private int[] f9 = {7, 16, 35, 62, 77, 84, 85, 87, 88, 90};
    /**
     * 其他
     */
    private int[] f10 = {19, 44, 59, 60, 64, 66, 89};

    @Override
    public EvaluationResult calculate(List<Integer> answers) {
        if (answers.size() != ANSWER_NUM) {
            throw new IllegalArgumentException("问卷答案错误，应当有" + ANSWER_NUM + "个答案");
        }
        EvaluationResult result = new EvaluationResult();
        result.setEvaluationTableId(EvaluationTableEnum.SCL_90.getCode());
        List<SCL90FactorResult> list = new ArrayList<>();
        getFactorList().forEach(factor -> calFactor(answers, factor, list));

        //有项目>=3分
        Boolean abnormal = false;

        //总分 所有项目总得分
        int sum = answers.stream().mapToInt(Integer::intValue).sum();
        //阳性 测试项目选择>=2的
        int positive = 0;
        for (Integer answer : answers) {
            if (answer >= 2) {
                positive++;
                if (answer > 2) {
                    abnormal = true;
                }
            }
        }
        //阴性 测试项目选择<2的
        int negative = ANSWER_NUM - positive;
        //阳性项目均分
        double avgPositiveScore = avgPositiveScore(answers, positive);

        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("SCL-90问卷结果如下  ");
        stringBuilder.append("总分：").append(sum).append(";");
        stringBuilder.append("总症状指数：").append(String.format("%.2f", sum * 1.0 / ANSWER_NUM)).append(";");
        if (sum > 160) {
            stringBuilder.append("总分超过160，需要进行检查").append(";");
        }
        stringBuilder.append("阳性数量：").append(positive).append(";");
        if (positive > 43) {
            stringBuilder.append("阳性数量超过43，需要进行检查").append(";");
        }
        stringBuilder.append("阴性数量：").append(negative).append(";");
        stringBuilder.append("阳性项目均分：").append(String.format("%.2f", avgPositiveScore)).append(";");
        if (abnormal) {
            stringBuilder.append("有项目分数超过2，需要进行检查").append(";");
        }
        stringBuilder.append("躯体化均分： ").append(String.format("%.2f", list.get(0).getAvgFactorScore())).append(";");
        stringBuilder.append("强迫症状均分：").append(String.format("%.2f", list.get(1).getAvgFactorScore())).append(";");
        stringBuilder.append("人际关系敏感均分 ").append(String.format("%.2f", list.get(2).getAvgFactorScore())).append(";");
        stringBuilder.append("抑郁均分： ").append(String.format("%.2f", list.get(3).getAvgFactorScore())).append(";");
        stringBuilder.append("焦虑均分：").append(String.format("%.2f", list.get(4).getAvgFactorScore())).append(";");
        stringBuilder.append("敌对均分： ").append(String.format("%.2f", list.get(5).getAvgFactorScore())).append(";");
        stringBuilder.append("恐怖均分：").append(String.format("%.2f", list.get(6).getAvgFactorScore())).append(";");
        stringBuilder.append("偏执均分：").append(String.format("%.2f", list.get(7).getAvgFactorScore())).append(";");
        stringBuilder.append("精神病性均分： ").append(String.format("%.2f", list.get(8).getAvgFactorScore())).append(";");
        stringBuilder.append("其他均分（睡眠饮食）： ").append(String.format("%.2f", list.get(9).getAvgFactorScore())).append(";");

        result.setScore(sum);
        result.setInfo(stringBuilder.toString());
        return result;
    }

    private double avgPositiveScore(List<Integer> answers, int positive) {
        int positiveSum = answers.stream().filter(i -> i >= 2).mapToInt(Integer::intValue).sum();
        return positiveSum * 1.0 / positive;
    }

    private void calFactor(List<Integer> answers, int[] factor, List<SCL90FactorResult> list) {
        int total = 0;
        for (int j : factor) {
            Integer score = answers.get(j - 1);
            total += score;
        }
        double avg = total * 1.0 / factor.length;
        SCL90FactorResult factorResult = new SCL90FactorResult(total, avg);
        list.add(factorResult);
    }

    private List<int[]> getFactorList() {
        List<int[]> list = new ArrayList<>();
        list.add(f1);
        list.add(f2);
        list.add(f3);
        list.add(f4);
        list.add(f5);
        list.add(f6);
        list.add(f7);
        list.add(f8);
        list.add(f9);
        list.add(f10);
        return list;
    }
}
