package com.example.mybatisplus.service.param;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
public class EvaluationTableParam {

    @ApiModelProperty(value = "评测表id")
    private Long tableId;

    @ApiModelProperty(value = "所填答案")
    private List<Integer> answers;

    @ApiModelProperty(value = "学生id")
    private Long stuId;
}
