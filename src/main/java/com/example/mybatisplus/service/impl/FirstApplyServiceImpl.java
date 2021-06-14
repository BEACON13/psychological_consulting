package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.FirstApply;
import com.example.mybatisplus.mapper.FirstApplyMapper;
import com.example.mybatisplus.service.FirstApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-14
 */
@Service
public class FirstApplyServiceImpl extends ServiceImpl<FirstApplyMapper, FirstApply> implements FirstApplyService {
    @Override
    public int insertFirstApply(FirstApply firstApply) {
        return baseMapper.insert(firstApply);
    }

    @Override
    public List<FirstApply> getFirstApplyByStu(Long sId) {
        QueryWrapper<FirstApply> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(FirstApply::getSId, sId);
        return baseMapper.selectList(wrapper);
    }
}
