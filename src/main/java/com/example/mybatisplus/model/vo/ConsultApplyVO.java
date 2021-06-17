package com.example.mybatisplus.model.vo;

import lombok.Data;

import java.time.LocalTime;

@Data
public class ConsultApplyVO {
    private Long consultApplyId;

    private Long sId;

    private String stuName;

    private String phone;

    private String address;

    private String emergencyPhone;

    private String dangerLevel;

    private String problemType;

    private Integer tpId1;

    private Integer tpId2;

    private Integer tpId3;

    private Integer num;

    private Boolean isFinished;

    private LocalTime startTime1;

    private Integer duration1;

    private LocalTime StartTime2;

    private Integer duration2;

    private LocalTime StartTime3;

    private Integer duration3;
}
