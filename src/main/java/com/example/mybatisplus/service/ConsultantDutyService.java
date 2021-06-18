package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.ConsultantDuty;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;

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

    JsonResponse showConsultantDuty();

    JsonResponse alterConsultantDuty(Integer tpID,Long cID,Long lID);

    JsonResponse deleteConsultantDuty(Long cdID, Integer tpID, Long cID);

    LocalDate getFreeTime(Integer tpId,Long cId);

    Long getLocation(Integer tpId,Long cId);

    int updateFreeTime(Integer tpId,Long cId,LocalDate newFreeDate);
}
