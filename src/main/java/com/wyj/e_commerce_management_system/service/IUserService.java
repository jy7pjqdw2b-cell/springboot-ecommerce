package com.wyj.e_commerce_management_system.service;

import com.wyj.e_commerce_management_system.dto.UserLoginDTO;
import com.wyj.e_commerce_management_system.dto.UserRegisterDTO;
import com.wyj.e_commerce_management_system.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wyj.e_commerce_management_system.vo.UserVO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author wyj
 * @since 2025-10-29
 */
public interface IUserService extends IService<User> {

    void register(UserRegisterDTO dto);

    // 用户登录（返回JWT令牌）
    String login(UserLoginDTO dto);

    // 查询当前登录用户信息
    UserVO getCurrentUser(String phone);  // 从JWT中解析出手机号，查询用户
}
