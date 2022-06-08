package com.liumulin.controllers;


import com.liumulin.entity.UserInfo;
import com.liumulin.exceptions.CommonResultCode;
import com.liumulin.exceptions.CustomException;
import com.liumulin.mapper.UserInfoMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 *  前端控制器
 * </p>
 *
 * @author Daniel Liu
 * @since 2022-06-07
 */
@RestController
@RequestMapping("/user-info")
public class UserInfoController {

    @Autowired
    private UserInfoMapper userInfoMapper;

    @GetMapping("/all")
    public List<UserInfo> all(){
        System.out.println("111");
        int i = 1/0;
//        throw new CustomException(CommonResultCode.CONNECT_ERROR);
        return userInfoMapper.selectList(null);
    }
}
