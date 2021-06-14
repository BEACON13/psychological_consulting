package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Person;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author Kristy
 * @since 2021-06-11
 */
@Repository
public interface PersonMapper extends BaseMapper<Person> {

    Person login(@Param("username")String username, @Param("type")String type);

}
