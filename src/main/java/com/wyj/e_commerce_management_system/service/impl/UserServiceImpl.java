package com.wyj.e_commerce_management_system.service.impl;

import com.wyj.e_commerce_management_system.entity.User;
import com.wyj.e_commerce_management_system.mapper.UserMapper;
import com.wyj.e_commerce_management_system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 用户表 服务实现类
 * </p>
 *
 * @author wyj
 * @since 2025-10-29
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements IUserService {

}
