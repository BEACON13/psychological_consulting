package com.example.mybatisplus.service;

import com.example.mybatisplus.model.domain.Person;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-11
 */
public interface PersonService extends IService<Person> {

    Person login(String username,String type);
}
