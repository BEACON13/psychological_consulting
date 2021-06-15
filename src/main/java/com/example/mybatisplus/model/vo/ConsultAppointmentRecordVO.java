package com.example.mybatisplus.model.vo;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.example.mybatisplus.model.domain.ConsultApply;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalDate;
import java.time.LocalTime;


@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class ConsultAppointmentRecordVO extends ConsultAppointmentRecord {
    private static final long serialVersionUID = 1L;

    private Long consultAppointId;

    private Long sId;

    private Integer tpId;

    private Long locationId;

    private Long cId;

    private Boolean isDeleted;

    private LocalDate date;

    private Boolean isFinished;

    private String consultantName;

    private LocalTime startTime;

    private Integer duration;

    private String locationName;

    private ConsultApply consultApply;


}
