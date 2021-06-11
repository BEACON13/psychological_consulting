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

import javax.servlet.http.HttpSession;
import java.util.Map;


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
     * 登录
     *
     */
    @RequestMapping(value = "/login", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse login(@RequestParam("username")String username,@RequestParam("pwd")String pwd,@RequestParam("type")String type){
        /*Map<String,String> map = (Map<String, String>) form;
        Person person = personService.login(map.get("username"),map.get("type"));*/
        Person person = personService.login(username,type);
        if (person == null)
            return JsonResponse.failure("用户不存在");
        else if(!person.getPassword().equals(pwd))
            return JsonResponse.failure("密码错误");
        return JsonResponse.success(person,"登录成功！");


    }

    /**
    * 描述：根据Id 查询
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.GET)
    @ResponseBody
    public JsonResponse getById(@PathVariable("id") Long id)throws Exception {
        Person  person =  personService.getById(id);
        return JsonResponse.success(person);
    }

    /**
    * 描述：根据Id删除
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public JsonResponse deleteById(@PathVariable("id") Long id) throws Exception {
        personService.removeById(id);
        return JsonResponse.success(null);
    }


    /**
    * 描述：根据Id 更新
    *
    */
    @RequestMapping(value = "/{id}", method = RequestMethod.PUT)
    @ResponseBody
    public JsonResponse updatePerson(@PathVariable("id") Long  id,Person  person) throws Exception {
        person.setPId(id);
        personService.updateById(person);
        return JsonResponse.success(null);
    }


    /**
    * 描述:创建Person
    *
    */
    @RequestMapping(value = "", method = RequestMethod.POST)
    @ResponseBody
    public JsonResponse create(Person  person) throws Exception {
        personService.save(person);
        return JsonResponse.success(null);
    }
}

