package com.wyj.e_commerce_management_system.service.impl;

import com.wyj.e_commerce_management_system.entity.Product;
import com.wyj.e_commerce_management_system.mapper.ProductMapper;
import com.wyj.e_commerce_management_system.service.IProductService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 商品表 服务实现类
 * </p>
 *
 * @author wyj
 * @since 2025-10-29
 */
@Service
public class ProductServiceImpl extends ServiceImpl<ProductMapper, Product> implements IProductService {

}
