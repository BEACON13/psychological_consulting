package com.example.mybatisplus.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.common.consts.EvaluationTableEnum;
import com.example.mybatisplus.mapper.EvaluationTableMapper;
import com.example.mybatisplus.model.domain.EvaluationResult;
import com.example.mybatisplus.model.domain.EvaluationTable;
import com.example.mybatisplus.service.EvaluationCalculateService;
import com.example.mybatisplus.service.EvaluationResultService;
import com.example.mybatisplus.service.EvaluationTableService;
import com.example.mybatisplus.service.param.EvaluationTableParam;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.time.LocalDateTime;
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

    @Resource
    private EvaluationResultService evaluationResultService;

    @Override
    public List<EvaluationTable> listAllEvaluations() {
        return this.baseMapper.listAllEvaluations();
    }

    @Override
    public EvaluationResult evaluate(EvaluationTableParam param) {
        if (param == null || param.getTableId() == null || param.getAnswers() == null || param.getStuId() == null) {
            return null;
        }
        EvaluationCalculateService evaluationCalculateService = null;
        if (EvaluationTableEnum.PHQ_9.getCode().equals(param.getTableId())) {
            evaluationCalculateService = new PHQ9ServiceImpl();
        } else if (EvaluationTableEnum.SCL_90.getCode().equals(param.getTableId())) {
            evaluationCalculateService = new SCL90ServiceImpl();
        }
        EvaluationResult result = null;
        if (evaluationCalculateService != null) {
            result = evaluationCalculateService.calculate(param.getAnswers());
            if (result != null) {
                result.setStuId(param.getStuId());
                result.setEvaluateTime(LocalDateTime.now());
                evaluationResultService.save(result);
            }
        }
        return result;
    }

    @Override
    public EvaluationTable getEvaluationById(Long id) {
        QueryWrapper<EvaluationTable> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(EvaluationTable::getId, id);
        return baseMapper.selectOne(wrapper);
    }


}
