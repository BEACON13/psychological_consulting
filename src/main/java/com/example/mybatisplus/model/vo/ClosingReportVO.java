package com.example.mybatisplus.model.vo;


import lombok.Data;
import lombok.experimental.Accessors;

@Accessors(chain = true)
@Data
public class ClosingReportVO {

    private static final long serialVersionUID = 1L;

    //ClosingReport字段

    private Long closingReportId;

    private Long sId;

    private Long cId;

    private String problemType;

    private Integer consultNum;

    private String consultEffectSelf;

    //VO字段

    private String stuName;

    private String consultantName;

}
