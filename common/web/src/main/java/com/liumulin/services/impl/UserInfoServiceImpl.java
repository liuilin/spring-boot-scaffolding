package com.liumulin.services.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.liumulin.entity.UserInfo;
import com.liumulin.mapper.UserInfoMapper;
import com.liumulin.services.IUserInfoService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author Daniel Liu
 * @since 2022-06-07
 */
@Service
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements IUserInfoService {

}
