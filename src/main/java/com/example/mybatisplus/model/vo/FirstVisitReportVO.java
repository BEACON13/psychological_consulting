package com.example.mybatisplus.model.vo;

import com.example.mybatisplus.model.domain.FirstVisitReport;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class FirstVisitReportVO {

    private Long fvreportId;

    private Long fvrId;

    private Long sId;

    private Integer tpId;

    private Long fvId;

    private String dangerLevel;

    private String problemType;

    private String conclusion;

    private LocalDate date;

    private String stuname;

    private LocalTime startTime;

    private Integer duration;

    private String fvName;

}
