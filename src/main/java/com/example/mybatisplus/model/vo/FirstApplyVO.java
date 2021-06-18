package com.example.mybatisplus.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FirstApplyVO {

    //原有字段
    private Long faId;

    private Long sId;

    private Integer tpId;

    private Integer score;

    private String name;

    private String phone;

    private String address;

    private String emergencyPhone;

    private String physicalIllness;

    private Boolean isDiagnosed;

    private String emergencyLevel;

    private String problemType;

    private String consultExpectation;

    private String consultHistory;

    private Boolean isFinished;

    //VO字段

    private LocalTime startTime;

    private Integer duration;

    private Integer weekday;

}
