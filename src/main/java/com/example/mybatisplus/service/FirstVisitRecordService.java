package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.FirstVisitRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.vo.FirstVisitRecordVO;

import java.time.LocalDate;
import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-13
 */
public interface FirstVisitRecordService extends IService<FirstVisitRecord> {


    JsonResponse showRecords();

    JsonResponse showAllRecords();

    JsonResponse getRecordsByName(String stuName);

    List<FirstVisitRecord> getRecordByStudent(Long sId);

    JsonResponse getFVRecord();

    JsonResponse manageFVRecord(Long fvrId);

    List<FirstVisitRecordVO> getAllRecordsAdmin();

    int insertFVRecord(FirstVisitRecord record);

    List<FirstVisitRecordVO> getRecordByFirstVisitorName(String name);

    List<FirstVisitRecordVO> getRecordByFirstVisitorNameUnfinished(String name);

    List<FirstVisitRecordVO> getRecordByStuName(String name);

    LocalDate getFirstVisitDate(int tpId);

    List<FirstVisitRecordVO> getUnfinishedRecord();
}
