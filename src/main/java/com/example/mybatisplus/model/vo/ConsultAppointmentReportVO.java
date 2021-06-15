package com.example.mybatisplus.model.vo;


import lombok.Data;

import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;


@Accessors(chain = true)
@Data
public class ConsultAppointmentReportVO {

    private static final long serialVersionUID = 1L;

    //ConsultAppointmentReport字段

    private Long carId;

    private Long consultAppointId;

    private Long sId;

    private Integer tpId;

    private Long cId;

    private String consultResult;

    private LocalDate date;

    //VO字段
    private String stuName;

    private LocalTime startTime;

    private Integer duration;

    private String consultantName;


}
