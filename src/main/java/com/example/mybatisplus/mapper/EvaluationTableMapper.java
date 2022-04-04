package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.EvaluationTable;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Beacon
 * @since 2022-04-04
 */
public interface EvaluationTableMapper extends BaseMapper<EvaluationTable> {

    /**
     * 获取所有评价表
     * @return 评价表列表
     */
    List<EvaluationTable> listAllEvaluations();
}
