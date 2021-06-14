package com.example.mybatisplus.model.vo;

import com.example.mybatisplus.model.domain.FirstApply;
import com.example.mybatisplus.model.domain.FirstVisitRecord;

import java.time.LocalTime;

public class FirstVisitRecordVO extends FirstVisitRecord {

    private LocalTime startTime;

    private Integer duration;

    private String locationName;

    private String fvName;

    private FirstApply firstApply;

    @Override
    public String toString() {
        return super.toString() + "FirstVisitRecordVO{" +
                "startTime=" + startTime +
                ", duration=" + duration +
                ", locationName='" + locationName + '\'' +
                ", fvName='" + fvName + '\'' +
                ", firstApply=" + firstApply +
                '}';
    }
}
