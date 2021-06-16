package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.TimePeriod;
import com.baomidou.mybatisplus.extension.service.IService;

import java.sql.Time;
import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-15
 */
public interface TimePeriodService extends IService<TimePeriod> {
    JsonResponse getTimePeriod();

}
