package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.FirstVisitReport;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-14
 */
public interface FirstVisitReportService extends IService<FirstVisitReport> {
    JsonResponse insertFVReport(Map form);

    JsonResponse showAllFVReports();

    JsonResponse getFVReportsByName(String stuName);


    List<FirstVisitReport> getFVReportsBySId(Long SID);

    JsonResponse getFVReportsByFvrId(Long fvrId);


}
