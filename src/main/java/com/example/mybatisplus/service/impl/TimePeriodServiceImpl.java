package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
}
