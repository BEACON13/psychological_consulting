package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.mybatisplus.mapper.EvaluationResultMapper;
import com.example.mybatisplus.model.domain.EvaluationResult;
import com.example.mybatisplus.model.domain.EvaluationTable;
import com.example.mybatisplus.model.vo.EvaluationResultVO;
import com.example.mybatisplus.service.EvaluationResultService;
import com.example.mybatisplus.service.EvaluationTableService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
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
public class EvaluationResultServiceImpl extends ServiceImpl<EvaluationResultMapper, EvaluationResult> implements EvaluationResultService {

    @Resource
    EvaluationTableService evaluationTableService;

    @Override
    public List<EvaluationResultVO> listEvaluationResult(Long studentId) {
        QueryWrapper<EvaluationResult> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(EvaluationResult::getStuId, studentId).orderByDesc(EvaluationResult::getId);
        List<EvaluationResult> results = baseMapper.selectList(wrapper);
        List<EvaluationResultVO> vos = new ArrayList<>();
        results.forEach(result -> {
            EvaluationTable evaluationTable = evaluationTableService.getEvaluationById(result.getEvaluationTableId());
            EvaluationResultVO vo = new EvaluationResultVO();
            BeanUtils.copyProperties(result, vo);
            vo.setEvaluationName(evaluationTable.getEvaluationName());
            vo.setDescription(evaluationTable.getDescription());
            vo.setRule(evaluationTable.getRule());
            vos.add(vo);
        });
        return vos;
    }

    @Override
    public EvaluationResultVO getOneEvaluationResult(Long studentId, Long evaluationId) {
        EvaluationResult result = this.baseMapper.getEvaluationResult(studentId, evaluationId);
        EvaluationResultVO vo = new EvaluationResultVO();
        EvaluationTable evaluationTable = evaluationTableService.getEvaluationById(evaluationId);
        if (result == null) {
            vo.setInfo("未进行评测");
        } else {
            BeanUtils.copyProperties(result, vo);
        }
        vo.setEvaluationName(evaluationTable.getEvaluationName());
        vo.setDescription(evaluationTable.getDescription());
        vo.setRule(evaluationTable.getRule());
        return vo;
    }

    @Override
    public EvaluationResultVO getLastEvaluationResult(Long studentId) {
        EvaluationResult result = this.baseMapper.getLastEvaluationResult(studentId);
        EvaluationResultVO vo = new EvaluationResultVO();
        if (result == null) {
            vo.setInfo("未进行评测");
        } else {
            BeanUtils.copyProperties(result, vo);
            EvaluationTable evaluationTable = evaluationTableService.getEvaluationById(result.getEvaluationTableId());
            vo.setEvaluationName(evaluationTable.getEvaluationName());
            vo.setDescription(evaluationTable.getDescription());
            vo.setRule(evaluationTable.getRule());
        }
        return vo;
    }


}
