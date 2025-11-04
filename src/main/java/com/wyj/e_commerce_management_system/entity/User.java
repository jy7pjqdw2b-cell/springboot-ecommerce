package com.wyj.e_commerce_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author wyj
 * @since 2025-10-29
 */
@Getter
@Setter
@TableName("user")
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 用户ID（自增主键）
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 用户名（唯一，用于登录）
     */
    @TableField("username")
    private String username;

    /**
     * 密码（加密后存储，比如BCrypt加密）
     */
    @TableField("password")
    private String password;

    /**
     * 手机号（用于找回密码）
     */
    @TableField("phone")
    private String phone;

    /**
     * 默认地址（简化设计，后续可拆成地址表）
     */
    @TableField("address")
    private String address;

    /**
     * 角色（USER-普通用户，ADMIN-管理员）
     */
    @TableField("role")
    private String role;

    /**
     * 创建时间
     */
    @TableField("create_time")
    private LocalDateTime createTime;

    /**
     * 更新时间
     */
    @TableField("update_time")
    private LocalDateTime updateTime;
}
