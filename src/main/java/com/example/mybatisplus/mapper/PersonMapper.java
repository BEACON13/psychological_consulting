package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kristy
 * @since 2021-06-16
 */
@Repository
public interface PersonMapper extends BaseMapper<Person> {

    Person login(@Param("username")String username, @Param("type")String type);

    void insertPerson(Person person);

    List<Person> getPersonByType(String type);
}
