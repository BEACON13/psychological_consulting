package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.domain.EvaluationResult;
import com.example.mybatisplus.model.vo.EvaluationResultVO;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Beacon
 * @since 2022-04-04
 */
public interface EvaluationResultService extends IService<EvaluationResult> {

    public List<EvaluationResultVO> listEvaluationResult(Long studentId);
}
