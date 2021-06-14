package com.example.mybatisplus.model.dto;

import lombok.Data;

@Data
public class UserInfoDTO {
    private Long id;
    private String username;
    private String pwd;
    private Long userType;
}
