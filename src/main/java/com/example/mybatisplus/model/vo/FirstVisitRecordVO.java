package com.example.mybatisplus.model.vo;

import com.example.mybatisplus.model.domain.FirstApply;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalTime;

@Data
public class FirstVisitRecordVO {


    private Long fvrId;

    private Long sId;

    private Integer tpId;

    private Long locationId;

    private Long fvId;

    private LocalDate date;

    private Boolean isDeleted;

    private Boolean isFinished;

    private LocalTime startTime;

    private Integer duration;

    private String locationName;

    private String fvName;

    private FirstApply firstApply;

    private String evaluateInfo;
}
