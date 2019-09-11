package com.dsf.mp.service.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dsf.mp.service.dao.UserMapper;
import com.dsf.mp.service.entity.User;
import com.dsf.mp.service.service.IUserService;
import org.springframework.stereotype.Service;

@Service
public class UserService extends ServiceImpl<UserMapper,User> implements IUserService {

}
