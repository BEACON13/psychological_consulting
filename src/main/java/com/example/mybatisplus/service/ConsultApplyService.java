package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.ConsultApply;
import com.baomidou.mybatisplus.extension.service.IService;

import java.time.LocalDate;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */
public interface ConsultApplyService extends IService<ConsultApply> {

    JsonResponse isAllowedtoApply();

    JsonResponse applyConsult(Integer tp1, Integer tp2, Integer tp3);

    JsonResponse showAllApplies();

    JsonResponse showApplyByStuName(String stuName);

    JsonResponse showUnfinishedApplies();

    JsonResponse showUnfinishedApplyByStuName(String stuName);

    JsonResponse handleApply(Map form);

    void sendMessage();
}

