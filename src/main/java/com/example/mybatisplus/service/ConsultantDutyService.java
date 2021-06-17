package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.ConsultantDuty;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-17
 */
public interface ConsultantDutyService extends IService<ConsultantDuty> {
    void checkFreeTime(Integer tpID,Long cID);

    JsonResponse showFreeTime(Integer tpID);
}
