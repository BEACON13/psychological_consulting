package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
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

    @Override
    public Student login(String code){
        QueryWrapper<Student> wrapper = new QueryWrapper();
        wrapper.lambda().eq(Student::getCode,code);
        List<Student> studentList = baseMapper.selectList(wrapper);
        return studentList.get(0);
    }

    @Override
    public boolean changePwd(Long id,String NewPwd) {
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        Student stu=new Student();
        stu.setSId(id).setPassword(NewPwd);
        return baseMapper.updateById(stu)==1;
    }
}
