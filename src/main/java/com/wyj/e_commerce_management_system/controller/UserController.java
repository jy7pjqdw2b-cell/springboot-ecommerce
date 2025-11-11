package com.wyj.e_commerce_management_system.controller;

import com.wyj.e_commerce_management_system.common.Result;
import com.wyj.e_commerce_management_system.dto.UserLoginDTO;
import com.wyj.e_commerce_management_system.dto.UserRegisterDTO;
import com.wyj.e_commerce_management_system.entity.User;
import com.wyj.e_commerce_management_system.service.IUserService;
import com.wyj.e_commerce_management_system.vo.UserVO;
import jakarta.annotation.Resource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author wyj
 * @since 2025-10-29
 */
@RestController
@RequestMapping("/user")
public class UserController {
    @Resource
    private IUserService userService;

    // 注册接口（POST请求）
    @PostMapping("/register")
    public Result register(@Validated @RequestBody UserRegisterDTO dto) {
        userService.register(dto);
        return Result.success("注册成功");
    }

    // 登录接口（POST请求）
    @PostMapping("/login")
    public Result<String> login(@Validated @RequestBody UserLoginDTO dto) {
        String token = userService.login(dto);
        return Result.success(token);  // 返回JWT令牌
    }

    // 查询当前用户信息（需要登录，GET请求）
    @GetMapping("/info")
    public Result<UserVO> getCurrentUser() {
        // 从Security上下文获取当前登录用户的手机号（JWT过滤器设置的）
        String phone = SecurityContextHolder.getContext().getAuthentication().getName();
        UserVO userVO = userService.getCurrentUser(phone);
        return Result.success(userVO);
    }
}
