package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.mapper.FirstVisitRecordMapper;
import com.example.mybatisplus.model.domain.FirstVisitRecord;
import com.example.mybatisplus.model.domain.FirstVisitReport;
import com.example.mybatisplus.mapper.FirstVisitReportMapper;
import com.example.mybatisplus.service.FirstVisitReportService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-14
 */
@Service
public class FirstVisitReportServiceImpl extends ServiceImpl<FirstVisitReportMapper, FirstVisitReport> implements FirstVisitReportService {
    @Autowired
    private FirstVisitReportMapper firstVisitReportMapper;
    @Autowired
    private FirstVisitRecordMapper firstVisitRecordMapper;

    @Override
    public JsonResponse insertFVReport(FirstVisitReport firstVisitReport) {
        firstVisitReportMapper.insert(firstVisitReport);
        UpdateWrapper<FirstVisitRecord> wrapper = new UpdateWrapper<>();
        wrapper.lambda().set(FirstVisitRecord::getIsFinished,1).eq(FirstVisitRecord::getSId,firstVisitReport.getSId());
        firstVisitRecordMapper.update(null,wrapper);
        return JsonResponse.successMessage("提交成功");
    }
}
