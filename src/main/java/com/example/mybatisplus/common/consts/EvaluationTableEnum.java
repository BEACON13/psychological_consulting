package com.example.mybatisplus.common.consts;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * @author Beacon
 */

@Getter
@AllArgsConstructor
public enum EvaluationTableEnum {
    /**
     * SCL-90
     */
    SCL_90(1L, "SCL-90评测表"),

    /**
     * PHQ-9
     */
    PHQ_9(2L, "PHQ-9评测表"),
    ;

    private Long code;
    private String name;
}
