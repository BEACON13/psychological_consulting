package com.example.mybatisplus.mapper;

import com.example.mybatisplus.model.domain.Student;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

/**
 * <p>
 * 学生表 Mapper 接口
 * </p>
 *
 * @author Beacon
 * @since 2021-06-11
 */
@Repository
public interface StudentMapper extends BaseMapper<Student> {
}
