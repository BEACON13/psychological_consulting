package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.ClosingReport;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.vo.ClosingReportVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */
public interface ClosingReportService extends IService<ClosingReport> {

    List<ClosingReportVO> getClosingReportByConAndStu(String stuName,Long cId);

    List<ClosingReportVO> getClosingReportByCon(Long cId);

    List<ClosingReportVO> getAllClosingReport();
}
