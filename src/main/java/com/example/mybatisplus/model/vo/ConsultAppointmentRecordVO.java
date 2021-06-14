package com.example.mybatisplus.model.vo;

import com.example.mybatisplus.model.domain.ConsultApply;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.time.LocalTime;

@EqualsAndHashCode(callSuper = true)
@Accessors(chain = true)
@Data
public class ConsultAppointmentRecordVO extends ConsultAppointmentRecord {

    private String consultantName;

    private LocalTime startTime;

    private Integer duration;

    private String locationName;

    private ConsultApply consultApply;

    @Override
    public String toString() {
        return "ConsultAppointmentRecordVO{" +
                "consultantName='" + consultantName + '\'' +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", locationName='" + locationName + '\'' +
                ", consultApply=" + consultApply +
                "} " + super.toString();
    }
}
