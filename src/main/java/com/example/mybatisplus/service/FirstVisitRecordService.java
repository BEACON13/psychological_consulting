package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.FirstVisitRecord;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.vo.FirstVisitRecordVO;

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
}
