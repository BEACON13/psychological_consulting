package com.example.mybatisplus.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.example.mybatisplus.common.JsonResponse;
import com.example.mybatisplus.common.utls.SecurityUtils;
import com.example.mybatisplus.common.utls.SessionUtils;
import com.example.mybatisplus.model.domain.Person;
import com.example.mybatisplus.mapper.PersonMapper;
import com.example.mybatisplus.service.PersonService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author Kristy
 * @since 2021-06-16
 */
@Service
public class PersonServiceImpl extends ServiceImpl<PersonMapper, Person> implements PersonService {
    @Autowired
    PersonMapper personMapper;

    @Override
    public JsonResponse login(String username, String type, String pwd) {
        Person person = personMapper.login(username,type);
        if (person == null)
            return JsonResponse.failure("用户不存在");
        else if(!person.getPassword().equals(pwd))
            return JsonResponse.failure("密码错误");
        SessionUtils.saveCurrentUserInfo(person);
        return JsonResponse.success(person,"登录成功！");
    }

    @Override
    public JsonResponse modifyPwd(String newPwd) {
        Long id = SecurityUtils.getUserInfo().getId();
        UpdateWrapper<Person> wrapper = new UpdateWrapper<>();
        wrapper.set("password",newPwd).eq("p_id",id);
        personMapper.update(null,wrapper);
        return JsonResponse.successMessage("修改成功!");
    }

    /*
     * 插入Person
     */
    @Override
    public void insertPerson(Person person) {
        personMapper.insertPerson(person);
    }

    /*
     * 展示所有Person
     */
    @Override
    public List<Person> showAllPerson() {
        QueryWrapper<Person> wrapper = new QueryWrapper<>();
        return baseMapper.selectList(wrapper);
    }

    /*
     * 展示所有type人员
     * e.g. type=”初访员“ 则返回所有初访员Person信息
     */
    @Override
    public List<Person> showPersonByType(String type) {

        return personMapper.getPersonByType(type);
    }

    /*
     * 修改Person信息
     */
    @Override
    public int updatePerson(Person person) {
        UpdateWrapper<Person> wrapper = new UpdateWrapper<>();

        return 0;
    }

}
