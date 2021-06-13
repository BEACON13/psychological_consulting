package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.FirstVisitRecord;
import com.example.mybatisplus.model.domain.FirstVisitReport;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-13
 */
public interface FirstVisitReportService extends IService<FirstVisitReport> {
    JsonResponse insertFVReport(FirstVisitReport firstVisitReport);
}
