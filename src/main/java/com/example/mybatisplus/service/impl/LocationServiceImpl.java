package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Location;
import com.example.mybatisplus.mapper.LocationMapper;
import com.example.mybatisplus.service.LocationService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-18
 */
@Service
public class LocationServiceImpl extends ServiceImpl<LocationMapper, Location> implements LocationService {

    @Autowired
    LocationMapper locationMapper;
    /**
     * 描述：返回地点安排
     *
     */
    @Override
    public JsonResponse getLocation(Integer type) {
        QueryWrapper<Location> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Location::getLocationType,type);
        List<Location> locations = locationMapper.selectList(wrapper);
        return JsonResponse.success(locations,"success!");
    }
}
