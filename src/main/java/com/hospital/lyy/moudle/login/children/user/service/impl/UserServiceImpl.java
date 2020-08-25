package com.hospital.lyy.moudle.login.children.user.service.impl;

import com.hospital.lyy.moudle.login.children.user.beans.User;
import com.hospital.lyy.moudle.login.children.user.mapper.UserMapper;
import com.hospital.lyy.moudle.login.children.user.service.UserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lj
 * @since 2020-08-23
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {

}
