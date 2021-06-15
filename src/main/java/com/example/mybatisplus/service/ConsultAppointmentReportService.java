package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.ConsultAppointmentReport;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */
public interface ConsultAppointmentReportService extends IService<ConsultAppointmentReport> {

    public int insertReport(ConsultAppointmentReport report);

    public Boolean checkLastRecordIsClosed(Long sId);
}
