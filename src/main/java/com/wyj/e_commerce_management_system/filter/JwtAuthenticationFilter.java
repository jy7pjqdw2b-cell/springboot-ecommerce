package com.wyj.e_commerce_management_system.filter;

import com.wyj.e_commerce_management_system.util.JwtUtil;
import jakarta.annotation.Resource;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;
import java.io.IOException;
import java.util.Collections;

@Component
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    @Resource
    private JwtUtil jwtUtil;

    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain
    ) throws ServletException, IOException {
        // 从请求头获取令牌（格式：Bearer token）
        String authHeader = request.getHeader("Authorization");
        if (authHeader == null || !authHeader.startsWith("Bearer ")) {
            filterChain.doFilter(request, response);  // 无令牌，直接放行（后续Security会拦截未认证请求）
            return;
        }

        // 提取令牌并验证
        String token = authHeader.substring(7);  // 去掉"Bearer "前缀
        if (!jwtUtil.validateToken(token)) {
            filterChain.doFilter(request, response);
            return;
        }

        // 解析令牌中的手机号，设置用户登录状态（Security上下文）
        String phone = jwtUtil.getPhoneFromToken(token);
        UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                phone, null, Collections.emptyList()  // 用户名（手机号）、密码（null）、权限（空）
        );
        authToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
        SecurityContextHolder.getContext().setAuthentication(authToken);  // 设置认证信息

        // 4. 继续执行过滤器链
        filterChain.doFilter(request, response);
    }
}
