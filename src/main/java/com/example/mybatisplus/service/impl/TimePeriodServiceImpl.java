package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.TimePeriod;
import com.example.mybatisplus.mapper.TimePeriodMapper;
import com.example.mybatisplus.service.TimePeriodService;
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
 * @since 2021-06-16
 */
@Service
public class TimePeriodServiceImpl extends ServiceImpl<TimePeriodMapper, TimePeriod> implements TimePeriodService {

    @Autowired
    TimePeriodMapper timePeriodMapper;

    /**
     * 描述：返回时间段安排
     *
     */
    @Override
    public JsonResponse getTimePeriod() {
        QueryWrapper<TimePeriod> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(TimePeriod::getIsDeleted,0);
        List<TimePeriod> timePeriods = timePeriodMapper.selectList(wrapper);
        return JsonResponse.success(timePeriods,"success!");
    }

    /*
     * 插入新时间段
     *
     */
    @Override
    public boolean insertTimePeriod(TimePeriod tp) {
        return this.save(tp);
    }

    /*
     * 修改时间段
     *
     */
    @Override
    public boolean updateTimePeriod(TimePeriod tp) {
        return this.updateById(tp);
    }

    /*
     * 删除时间段
     *
     */
    @Override
    public int deleteTimePeriod(Long tpId) {
        UpdateWrapper<TimePeriod> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(TimePeriod::getTpId,tpId)
                .set(TimePeriod::getIsDeleted,1);
        return baseMapper.delete(wrapper);
    }
}
