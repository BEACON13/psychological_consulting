package com.example.mybatisplus.model.vo;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AddConsultVO {

    //原有字段
    private Long addCId;

    private Long sId;

    private Long cId;

    private Integer tpId;

    private Integer times;

    private Boolean isFinished;

    //VO字段
    private String stuName;

    private String consultantName;

    private LocalTime startTime;

    private Integer duration;

}
