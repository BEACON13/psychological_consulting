package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.FirstVisitorDuty;
import com.example.mybatisplus.mapper.FirstVisitorDutyMapper;
import com.example.mybatisplus.model.vo.FirstVisitorDutyVO;
import com.example.mybatisplus.service.FirstVisitorDutyService;
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
 * @since 2021-06-15
 */
@Service
public class FirstVisitorDutyServiceImpl extends ServiceImpl<FirstVisitorDutyMapper, FirstVisitorDuty> implements FirstVisitorDutyService {

    @Autowired
    FirstVisitorDutyMapper firstVisitorDutyMapper;

    @Override
    public List<FirstVisitorDutyVO> getAllFVDuty() {
        return firstVisitorDutyMapper.getAllFVDuty();
    }

    @Override
    public List<FirstVisitorDutyVO> getAvailableFVDuty() {
        return firstVisitorDutyMapper.getAvailableFVDuty();
    }
}
