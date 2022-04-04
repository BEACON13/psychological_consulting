package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.EvaluationTable;
import com.baomidou.mybatisplus.extension.service.IService;

import javax.annotation.Resource;
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

}
