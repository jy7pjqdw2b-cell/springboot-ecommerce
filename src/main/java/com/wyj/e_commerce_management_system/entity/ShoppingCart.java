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
 * 购物车表
 * </p>
 *
 * @author wyj
 * @since 2025-10-29
 */
@Getter
@Setter
@TableName("shopping_cart")
public class ShoppingCart implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 关联用户ID（谁的购物车）
     */
    @TableField("user_id")
    private Long userId;

    /**
     * 关联商品ID（加了什么商品）
     */
    @TableField("product_id")
    private Long productId;

    /**
     * 数量（至少1件）
     */
    @TableField("quantity")
    private Integer quantity;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
