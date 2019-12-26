package com.example.service.impl;

import com.baomidou.dynamic.datasource.annotation.DS;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.example.entity.UserInfo;
import com.example.mapper.UserInfoMapper;
import com.example.service.UserInfoService;
import org.springframework.stereotype.Service;

/**
 * @author liuyzh
 * @description
 * @date 2019-12-26
 */
@Service
@DS("db3")
public class UserInfoServiceImpl extends ServiceImpl<UserInfoMapper, UserInfo> implements UserInfoService {
}
