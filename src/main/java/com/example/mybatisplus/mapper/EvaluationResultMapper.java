package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.domain.EvaluationResult;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Beacon
 * @since 2022-04-04
 */
public interface EvaluationResultMapper extends BaseMapper<EvaluationResult> {

    EvaluationResult getEvaluationResult(@Param("studentId") Long studentId, @Param("evaluationId") Long evaluationId);

    EvaluationResult getLastEvaluationResult(@Param("studentId") Long studentId);
}
