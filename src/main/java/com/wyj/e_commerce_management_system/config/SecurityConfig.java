package com.wyj.e_commerce_management_system.config;

import com.wyj.e_commerce_management_system.filter.JwtAuthenticationFilter;
import jakarta.annotation.Resource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Resource
    private JwtAuthenticationFilter jwtAuthFilter;  // 自定义JWT过滤器

    // 密码加密器（BCrypt）
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    // 配置接口权限
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                // 1. 禁用CSRF（前后端分离场景必选）
                .csrf(AbstractHttpConfigurer::disable)
                // 2. 配置无状态会话（JWT不需要session）
                .sessionManagement(session ->
                        session.sessionCreationPolicy(SessionCreationPolicy.STATELESS)
                )
                // 3. 授权请求配置
                .authorizeHttpRequests(auth ->
                        auth
                                // 放行注册、登录接口
                                .requestMatchers("/user/register", "/user/login").permitAll()
                                // 其他接口必须认证
                                .anyRequest().authenticated()
                )
                // 4. 添加JWT过滤器（在用户名密码过滤器前执行）
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class);

        return http.build();
    }
}
