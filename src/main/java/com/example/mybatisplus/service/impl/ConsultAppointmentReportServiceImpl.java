package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.ConsultAppointmentRecord;
import com.example.mybatisplus.model.domain.ConsultAppointmentReport;
import com.example.mybatisplus.mapper.ConsultAppointmentReportMapper;
import com.example.mybatisplus.service.ConsultAppointmentRecordService;
import com.example.mybatisplus.service.ConsultAppointmentReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */
@Service
public class ConsultAppointmentReportServiceImpl extends ServiceImpl<ConsultAppointmentReportMapper, ConsultAppointmentReport> implements ConsultAppointmentReportService {

    @Autowired
    ConsultAppointmentRecordService cars;

    @Override
    public int insertReport(ConsultAppointmentReport report) {
        save(report);
        cars.finishAppointment(report.getConsultAppointId());
        return 0;
    }

    /*
     *检查学生的最后一次咨询的结果是结案
     */
    @Override
    public Boolean checkLastRecordIsClosed(Long sId) {
        QueryWrapper<ConsultAppointmentReport> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(ConsultAppointmentReport::getSId,sId)
                .orderByDesc(ConsultAppointmentReport::getDate)
                .last("limit 1");

        ConsultAppointmentReport report = baseMapper.selectOne(wrapper);

        return report.getConsultResult().equals("结案");
    }

}
