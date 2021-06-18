package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.ClosingReport;
import com.example.mybatisplus.mapper.ClosingReportMapper;
import com.example.mybatisplus.model.vo.ClosingReportVO;
import com.example.mybatisplus.service.ClosingReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */
@Service
public class ClosingReportServiceImpl extends ServiceImpl<ClosingReportMapper, ClosingReport> implements ClosingReportService {

    @Autowired
    ClosingReportMapper crm;

    /*
     * 通过学生姓名和咨询师id获得结案报告
     */
    @Override
    public List<ClosingReportVO> getClosingReportByConAndStu(String stuName, Long cId) {
        return crm.getClosingReportByConAndStu(stuName,cId);
    }

    /*
     * 通过咨询师id获得结案报告
     */
    @Override
    public List<ClosingReportVO> getClosingReportByCon(Long cId) {
        return crm.getClosingReportByCon(cId);
    }

    @Override
    public List<ClosingReportVO> getAllClosingReport() {
        return crm.getAllClosingReport();
    }

    @Override
    public List<ClosingReportVO> getAllClosingReportByConName(String conName) {
        return crm.getAllClosingReportByConName(conName);
    }
}
