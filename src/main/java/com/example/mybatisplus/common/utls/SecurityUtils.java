package com.example.mybatisplus.common.utls;

import com.example.mybatisplus.model.domain.Person;
import com.example.mybatisplus.model.domain.Student;
import com.example.mybatisplus.model.dto.UserInfoDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class SecurityUtils {
    /**
     * 获取当前用户
     *
     * @return
     */

    public static Student getCurrentStudentInfo() {
        Student studentInfo = SessionUtils.getCurrentStudentInfo();
        //模拟登录
        if (studentInfo == null) {
            studentInfo = new Student();
            studentInfo.setName("模拟");
        }

        return studentInfo;
    }

    public static Person getCurrentUserInfo() {
        Person userInfo = SessionUtils.getCurrentUserInfo();
        //模拟登录
        if (userInfo == null) {
            userInfo = new Person();
            userInfo.setUsername("模拟");
        }

        return userInfo;
    }

    public static UserInfoDTO getUserInfo() {
        Person userInfo = SessionUtils.getCurrentUserInfo();
        UserInfoDTO userInfoDTO = new UserInfoDTO();
        //模拟登录
        if (userInfo == null) {
            userInfo = new Person();
            userInfo.setUsername("模拟用户");
            userInfoDTO.setId(1L);
            userInfoDTO.setUsername("模拟用户");
            userInfoDTO.setUserType(1L);
        }else{
            userInfoDTO.setId(userInfo.getPId());
            userInfoDTO.setUsername(userInfo.getUsername());
            userInfoDTO.setPwd(userInfo.getPassword());
            userInfoDTO.setUserType(1L);
        }

        return userInfoDTO;
    }


}
