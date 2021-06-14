package com.example.mybatisplus.web.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.PersonService;
import com.example.mybatisplus.model.domain.Person;


/**
 *
 *  前端控制器
 *
 *
 * @author Kristy
 * @since 2021-06-11
 * @version v1.0
 */
@Controller
@RequestMapping("/api/person")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger( PersonController.class );

    @Autowired
    private PersonService personService;

    /**
     * 描述：登录
     *
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse login(@RequestParam("username")String username,@RequestParam("pwd")String pwd,@RequestParam("type")String type){
        /*Map<String,String> map = (Map<String, String>) form;
        Person person = personService.login(map.get("username"),map.get("type"));*/
        return personService.login(username,type,pwd);

    }

    /**
     * 描述：修改用户密码
     *
     */
    @RequestMapping(value = "/modify", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse modifyPwd(@RequestParam("newPwd")String newPwd){
        return personService.modifyPwd(newPwd);
    }


}

