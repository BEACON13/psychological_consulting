package com.example.mybatisplus.web.controller;

import com.example.mybatisplus.model.domain.PersonType;
import com.example.mybatisplus.service.PersonTypeService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.stereotype.Controller;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.service.PersonService;
import com.example.mybatisplus.model.domain.Person;

import java.util.List;
import java.util.Map;


/**
 *
 *  前端控制器
 *
 *
 * @author Kristy
 * @since 2021-06-16
 * @version v1.0
 */
@Controller
@RequestMapping("/api/person")
public class PersonController {

    private final Logger logger = LoggerFactory.getLogger( PersonController.class );

    @Autowired
    private PersonService personService;

    @Autowired
    private PersonTypeService personTypeService;
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

    /*
     * 中心管理员增加用户
     */
    @RequestMapping(value = "admin/insert/user")
    @ResponseBody
    public JsonResponse insertUser(@RequestBody Map<String,Object> info){
        Person person = new Person();
        person.setUsername((String) info.get("username"))
                .setPassword((String) info.get("password"))
                .setName((String) info.get("name"))
                .setPhone((String) info.get("phone"))
                .setGender((String) info.get("gender"))
                .setJob((String) info.get("job"))
                .setAge((Integer) info.get("age"))
                .setInfo((String) info.get("info"))
                .setAddress((String) info.get("address"))
                .setEmail((String) info.get("email"));

        // 插入Person
        // 并通过MyBatis特性获取其主键于Person对象的pId中
        personService.insertPerson(person);

        //在PersonType中插入
        //由于一个用户可能具有多个type，所有map中的type应该是一个String链表
        Long pId = person.getPId();

        for (String type: (List<String>) info.get("type")) {
            PersonType pt = new PersonType();
            pt.setPId(pId).setType(type);
            personTypeService.save(pt);
        }

        return JsonResponse.successMessage("插入成功");
    }
}

