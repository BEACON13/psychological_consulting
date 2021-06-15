package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.ConsultApply;
import com.baomidou.mybatisplus.extension.service.IService;

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
}
