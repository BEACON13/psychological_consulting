package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Person;
import com.example.mybatisplus.mapper.PersonMapper;
import com.example.mybatisplus.service.PersonService;
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
 * @since 2021-06-11
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {

    @Autowired
    private PersonMapper personMapper;

    @Override
    public Person login(String username,String type) {
        Person person = personMapper.login(username,type);
        return person;
    }
}
