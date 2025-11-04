package com.wyj.e_commerce_management_system.controller;

import com.wyj.e_commerce_management_system.entity.Product;
import com.wyj.e_commerce_management_system.mapper.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * <p>
 * 商品表 前端控制器
 * </p>
 *
 * @author wyj
 * @since 2025-10-29
 */
@RestController
@RequestMapping("/product")
public class ProductController {
    @Autowired
    private ProductMapper productMapper;

    @GetMapping("/list")
    public List<Product> getProductList() {
        return productMapper.selectList(null); // 查询所有商品
    }
}
