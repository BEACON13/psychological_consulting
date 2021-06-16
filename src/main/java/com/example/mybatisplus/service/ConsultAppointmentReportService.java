package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.ConsultAppointmentReport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.vo.ConsultAppointmentReportVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */
public interface ConsultAppointmentReportService extends IService<ConsultAppointmentReport> {

    int insertReport(ConsultAppointmentReport report);

    Boolean checkLastRecordIsClosed(Long sId);

    List<ConsultAppointmentReportVO> getRecordByConAndStu(Long cId, String stuName);

    List<ConsultAppointmentReportVO> getRecordByCon(Long cId);
}
