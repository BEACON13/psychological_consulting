package com.example.mybatisplus.model.vo;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class FirstVisitorDutyVO {

    //原有字段

    private static final long serialVersionUID = 1L;

    private Long fvdId;

    private Integer tpId;

    private Long locationId;

    private Long fvId;

    private Boolean isAvailable;

    //VO字段

    private LocalTime startTime;

    private Integer duration;

    private String locationName;

    private String firstVisitorName;
}
