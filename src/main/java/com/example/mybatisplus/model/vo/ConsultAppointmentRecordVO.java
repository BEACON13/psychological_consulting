package com.example.mybatisplus.model.vo;

import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import lombok.Data;

import java.time.LocalTime;

@Data
public class ConsultAppointmentRecordVO extends ConsultAppointmentRecord {

    private String stuName;

    private String ConsultantName;

    private LocalTime startTime;

    private Integer duration;

    private String locationName;

    @Override
    public String toString() {
        return super.toString()+
                "ConsultAppointmentRecordVO{" +
                "stuName='" + stuName + '\'' +
                ", ConsultantName='" + ConsultantName + '\'' +
                ", startTime=" + startTime +
                ", duration=" + duration +
                ", locationName='" + locationName + '\'' +
                '}';
    }
}
