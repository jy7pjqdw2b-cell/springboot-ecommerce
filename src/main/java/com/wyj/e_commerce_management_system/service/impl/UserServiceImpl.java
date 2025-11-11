package com.wyj.e_commerce_management_system.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.wyj.e_commerce_management_system.dto.UserLoginDTO;
import com.wyj.e_commerce_management_system.dto.UserRegisterDTO;
import com.wyj.e_commerce_management_system.entity.User;
import com.wyj.e_commerce_management_system.mapper.UserMapper;
import com.wyj.e_commerce_management_system.service.IUserService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wyj.e_commerce_management_system.util.JwtUtil;
import com.wyj.e_commerce_management_system.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
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
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private PasswordEncoder passwordEncoder;  // SpringSecurity的BCrypt加密器
    @Resource
    private JwtUtil jwtUtil;  // 自定义JWT工具类

    @Override
    public void register(UserRegisterDTO dto) {
        // 校验手机号是否已注册
        User existUser = userMapper.selectByPhone(dto.getPhone());
        if (existUser != null) {
            throw new RuntimeException("手机号已注册");
        }

        //密码加密
        String encryptedPassword = passwordEncoder.encode(dto.getPassword());

        // 转换为实体类，保存到数据库
        User user = new User();
        user.setPhone(dto.getPhone());
        user.setPassword(encryptedPassword);  // 存加密后的密码
        user.setUsername(dto.getUsername());
        userMapper.insert(user);
    }

    @Override
    public String login(UserLoginDTO dto) {
        // 1. 查用户是否存在
        User user = userMapper.selectByPhone(dto.getPhone());
        if (user == null) {
            throw new RuntimeException("手机号或密码错误");
        }

        // 2. 校验密码（BCrypt匹配明文和加密后的密码）
        boolean passwordMatch = passwordEncoder.matches(dto.getPassword(), user.getPassword());
        if (!passwordMatch) {
            throw new RuntimeException("手机号或密码错误");
        }

        // 3. 生成JWT令牌（包含手机号，用于后续认证）
        return jwtUtil.generateToken(user.getPhone());
    }

    @Override
    public UserVO getCurrentUser(String phone) {
        // 按手机号查询用户，转换为VO返回
        User user = userMapper.selectByPhone(phone);
        return BeanUtil.copyProperties(user, UserVO.class);  // Hutool工具类拷贝属性
    }
}
