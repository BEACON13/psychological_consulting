package com.example.mybatisplus.service.impl;

import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.mapper.StudentMapper;
import com.example.mybatisplus.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 学生表 服务实现类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-11
 */
@Service
public class StudentServiceImpl extends ServiceImpl<StudentMapper, Student> implements StudentService {

}
