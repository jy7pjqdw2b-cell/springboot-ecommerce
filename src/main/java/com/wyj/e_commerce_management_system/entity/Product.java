package com.wyj.e_commerce_management_system.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;

/**
 * <p>
 * 商品表
 * </p>
 *
 * @author wyj
 * @since 2025-10-29
 */
@Getter
@Setter
@TableName("product")
public class Product implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品ID
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    /**
     * 商品名称（如"春季运动鞋"）
     */
    @TableField("name")
    private String name;

    /**
     * 商品价格（保留2位小数）
     */
    @TableField("price")
    private BigDecimal price;

    /**
     * 库存数量（>=0）
     */
    @TableField("stock")
    private Integer stock;

    /**
     * 商品描述（可选，比如"透气网面设计"）
     */
    @TableField("description")
    private String description;

    /**
     * 商品图片地址（存URL，前端直接展示）
     */
    @TableField("image_url")
    private String imageUrl;

    /**
     * 商品分类（如"男装""电子产品"）
     */
    @TableField("category")
    private String category;

    @TableField("create_time")
    private LocalDateTime createTime;

    @TableField("update_time")
    private LocalDateTime updateTime;
}
