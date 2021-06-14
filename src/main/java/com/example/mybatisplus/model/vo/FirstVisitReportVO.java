package com.example.mybatisplus.model.vo;

import com.example.mybatisplus.model.domain.FirstVisitReport;

import java.time.LocalTime;

public class FirstVisitReportVO extends FirstVisitReport {

    private String stuname;

    private LocalTime startTime;

    private Integer duration;

    private String fvName;

}
