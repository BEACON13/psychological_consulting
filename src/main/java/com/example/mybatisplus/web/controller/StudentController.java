package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.model.domain.Person;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.StudentService;
import com.example.mybatisplus.model.domain.Student;


/**
 *
 *  前端控制器
 *
 *
 * @author Beacon
 * @since 2021-06-11
 * @version v1.0
 */
@Controller
@RequestMapping("/api/student")
public class StudentController {

    private final Logger logger = LoggerFactory.getLogger( StudentController.class );

    @Autowired
    private StudentService studentService;

    /**
     * 登录
     *
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse login(@RequestParam("username")String code,@RequestParam("pwd")String pwd){
        return studentService.login(code,pwd);
    }

    /**
     * 修改密码
     *
     */
    @RequestMapping(value = "/changePwd", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse changePwd(@RequestParam("newPwd")String newPwd){
        Student student = SecurityUtils.getCurrentStudentInfo();
        if(studentService.changePwd(student.getCode(),newPwd) > 0)
            return JsonResponse.success("修改成功");
        return JsonResponse.failure("修改出错，请重试");
    }

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Student  student =  studentService.getById(id);
        return JsonResponse.success(student);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        studentService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updateStudent(@PathVariable("id") Long  id,Student  student) throws Exception {
        student.setSId(id);
        studentService.updateById(student);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Student
    *
    */
    @RequestMapping(value = "/ds", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Student  student) throws Exception {
        studentService.save(student);
        return JsonResponse.success(null);
    }
}

