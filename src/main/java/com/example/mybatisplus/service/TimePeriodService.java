package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.TimePeriod;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-16
 */
public interface TimePeriodService extends IService<TimePeriod> {

    JsonResponse getTimePeriod();
}
