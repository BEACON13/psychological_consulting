package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.model.domain.FirstApply;
import com.example.mybatisplus.model.domain.FirstVisitRecord;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.mapper.StudentMapper;
import com.example.mybatisplus.service.FirstApplyService;
import com.example.mybatisplus.service.FirstVisitRecordService;
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
    FirstApplyService firstApplyService;

    @Autowired
    FirstVisitRecordService firstVisitRecordService;

    @Override
    public JsonResponse login(String code,String pwd){

        QueryWrapper<Student> wrapper = new QueryWrapper();
        wrapper.lambda().eq(Student::getCode,code);
        Student student = baseMapper.selectOne(wrapper);

        if (student == null)
            return JsonResponse.failure("用户不存在");
        else if(!student.getPassword().equals(pwd))
            return JsonResponse.failure("密码错误");

        SessionUtils.saveCurrentUserInfo(student);

        return JsonResponse.success(student,"登录成功！");
    }

    @Override
    public int changePwd(String code,String newPwd) {
        UpdateWrapper<Student> wrapper = new UpdateWrapper();
        wrapper.lambda().eq(Student::getCode,code)
                .set(Student::getPassword,newPwd);
        return baseMapper.update(null,wrapper);
    }

    /*
    检查学生是否允许申请初访
     */
    @Override
    public boolean isAllowedFirstApply(Long id) {

        //如果学生已经具有咨询资格，则不应该申请初访
       if (isQualified(id))
           return false;

        //如果学生具有未完成的初访申请，则不应该申请初访
        List<FirstApply> applies = firstApplyService.getFirstApplyByStu(id);
        for (FirstApply a: applies) {

            if (!a.getIsFinished())
                return false;
        }

        //如果学生具有未完成的初访预约，则不应该申请初访
        List<FirstVisitRecord> records = firstVisitRecordService.getRecordByStudent(id);
        for (FirstVisitRecord r: records){
            if(!r.getIsFinished()){
                return false;
            }
        }

        return true;
    }

    @Override
    public boolean isQualified(Long id) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Student::getSId,id);
        Student stu = baseMapper.selectOne(wrapper);

        return stu.getIsQualified();
    }


    public Long getIdByName(String name) {
        QueryWrapper<Student> wrapper = new QueryWrapper<>();
        wrapper.lambda().eq(Student::getName,name);
        Student student = baseMapper.selectOne(wrapper);
        return student.getSId();
    }

    @Override
    public int setUnQualified(Long id) {
        UpdateWrapper<Student> wrapper = new UpdateWrapper<>();
        wrapper.lambda().eq(Student::getSId,id)
                .set(Student::getIsQualified,0);
        return baseMapper.update(null,wrapper);
    }


}
