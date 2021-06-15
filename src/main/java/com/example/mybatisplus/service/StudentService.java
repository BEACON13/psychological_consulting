package com.example.mybatisplus.service;

import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.model.domain.Person;
import com.example.mybatisplus.model.domain.Student;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 学生表 服务类
 * </p>
 *
 * @author Beacon
 * @since 2021-06-11
 */
public interface StudentService extends IService<Student> {

    JsonResponse login(String code,String pwd);

    int changePwd(String code,String newPwd);

    boolean isAllowedFirstApply(Long id);

    boolean isQualified(Long id);

    int setUnQualified(Long id);
}
