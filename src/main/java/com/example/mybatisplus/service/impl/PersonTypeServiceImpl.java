package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.PersonType;
import com.example.mybatisplus.mapper.PersonTypeMapper;
import com.example.mybatisplus.service.PersonTypeService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-16
 */
@Service
public class PersonTypeServiceImpl extends ServiceImpl<PersonTypeMapper, PersonType> implements PersonTypeService {

    /*
     * 根据person的ID，删除其person_type的记录
     */
    @Override
    public int deletePersonType(Long pId) {
        QueryWrapper<PersonType> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(PersonType::getPId,pId);
        return baseMapper.delete(wrapper);
    }
}
