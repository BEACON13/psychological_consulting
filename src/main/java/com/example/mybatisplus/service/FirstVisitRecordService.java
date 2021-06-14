package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.FirstVisitRecord;
import com.baomidou.mybatisplus.extension.service.IService;

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


    public JsonResponse showRecords();

    public JsonResponse showAllRecords();

    public JsonResponse getRecordsByName(String stuName);

    public List<FirstVisitRecord> getRecordByStudent(Long sId);

    public JsonResponse getFVRecord();
}
