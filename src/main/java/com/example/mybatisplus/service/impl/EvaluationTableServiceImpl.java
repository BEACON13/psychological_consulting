package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.EvaluationTable;
import com.example.mybatisplus.mapper.EvaluationTableMapper;
import com.example.mybatisplus.service.EvaluationTableService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Beacon
 * @since 2022-04-04
 */
@Service
public class EvaluationTableServiceImpl extends ServiceImpl<EvaluationTableMapper, EvaluationTable> implements EvaluationTableService {


    @Override
    public List<EvaluationTable> listAllEvaluations() {
        return this.baseMapper.listAllEvaluations();
    }
}
