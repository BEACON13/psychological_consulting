package com.example.mybatisplus.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.model.domain.EvaluationQuestion;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 * Mapper 接口
 * </p>
 *
 * @author Beacon
 * @since 2022-04-04
 */
public interface EvaluationQuestionMapper extends BaseMapper<EvaluationQuestion> {

    /**
     * 根据评估问卷id查询问题
     *
     * @param tableId
     * @return
     */
    List<EvaluationQuestion> listQuestions(@Param("tableId") Long tableId);
}
