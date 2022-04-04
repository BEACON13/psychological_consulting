package com.example.mybatisplus.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDateTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class EvaluationResultVO {

    private Long id;

    private Long stuId;

    private Long evaluationTableId;

    private Integer score;

    private String info;

    private LocalDateTime evaluateTime;

    /**
     * 评测表名称
     */
    private String evaluationName;

    /**
     * 评测表描述
     */
    private String description;

    /**
     * 评测表规则
     */
    private String rule;
}
