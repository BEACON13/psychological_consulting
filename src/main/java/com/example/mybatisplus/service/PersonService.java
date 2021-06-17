package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Person;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-16
 */
public interface PersonService extends IService<Person> {
    JsonResponse login(String username, String type,String pwd);

    JsonResponse modifyPwd(String newPwd);

    void insertPerson(Person person);

    List<Person> showAllPerson();

    List<Person> showPersonByType(String type);
}
