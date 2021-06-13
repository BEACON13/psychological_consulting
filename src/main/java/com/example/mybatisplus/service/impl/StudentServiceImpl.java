package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.mapper.StudentMapper;
import com.example.mybatisplus.service.StudentService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.xml.ws.wsaddressing.W3CEndpointReference;
import java.sql.Wrapper;
import java.util.List;

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

    @Autowired
    StudentMapper studentMapper;

    @Override
    public Student login(String code){
        //Student student=studentMapper.login(code);
        //return student;
        QueryWrapper wrapper=new QueryWrapper<Student>();
        wrapper.eq("code","2018141411216");
        List<Student> studentList = studentMapper.selectList(wrapper);
        return studentList.get(0);
    }
}
