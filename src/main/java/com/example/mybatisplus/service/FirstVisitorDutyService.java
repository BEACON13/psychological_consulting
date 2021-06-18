package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.FirstVisitorDuty;
import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.vo.FirstVisitorDutyVO;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-15
 */
public interface FirstVisitorDutyService extends IService<FirstVisitorDuty> {

    List<FirstVisitorDutyVO> getAllFVDuty();

    List<FirstVisitorDutyVO> getAvailableFVDuty();

    Boolean isConflic(Integer tpID,Long lID);

    JsonResponse alterFVDuty(Long fvdID, Integer tpID, Long fvID, Long lID);

    JsonResponse deleteFVDuty(Long fvdID, Integer tpID, Long fvID);

    JsonResponse insertFVDuty(Integer fvdID, Long fvID, Long lID);

}
