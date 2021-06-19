package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.model.domain.FirstApply;
import com.example.mybatisplus.mapper.FirstApplyMapper;
import com.example.mybatisplus.model.vo.FirstApplyVO;
import com.example.mybatisplus.service.FirstApplyService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
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

    @Autowired
    FirstApplyMapper firstApplyMapper;

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


    /*
     * 管理员获得紧急初访申请
     */
    @Override
    public List<FirstApplyVO> getUrgentApply() {
        return firstApplyMapper.getUrgentApply();
    }

    /*
     * 管理员获得普通初访申请
     */
    @Override
    public List<FirstApplyVO> getNormalApply() {
        return firstApplyMapper.getNormalApply();
    }

    /*
     * 在first apply完成后将其插入
     * 此时把first apply置为finish
     */
    @Override
    public int finishFirstApply(Long firstApplyId) {
        UpdateWrapper<FirstApply> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(FirstApply::getFaId,firstApplyId)
                .set(FirstApply::getIsFinished,1);
        return baseMapper.update(null,wrapper);
    }
}
