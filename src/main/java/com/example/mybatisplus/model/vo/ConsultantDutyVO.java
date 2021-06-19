package com.example.mybatisplus.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
@Accessors(chain=true)
public class ConsultantDutyVO {

    private Long cdId;

    private Integer tpId;

    private Long locationId;

    private Long cId;

    private LocalDate freeTime;

    private LocalTime startTime;

    private Integer duration;

    private Integer weekday;

    private String locationName;

    private String cName;
}
