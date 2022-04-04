package com.example.mybatisplus.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.example.mybatisplus.model.domain.EvaluationResult;
import com.example.mybatisplus.model.domain.EvaluationTable;
import com.example.mybatisplus.service.param.EvaluationTableParam;

import java.util.List;

/**
 * <p>
 * 服务类
 * </p>
 *
 * @author Beacon
 * @since 2022-04-04
 */
public interface EvaluationTableService extends IService<EvaluationTable> {

    List<EvaluationTable> listAllEvaluations();

    EvaluationResult evaluate(EvaluationTableParam param);

    EvaluationTable getEvaluationById(Long id);
}
