package com.wyj.e_commerce_management_system.service.impl;

import com.wyj.e_commerce_management_system.entity.Order;
import com.wyj.e_commerce_management_system.mapper.OrderMapper;
import com.wyj.e_commerce_management_system.service.IOrderService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 订单表 服务实现类
 * </p>
 *
 * @author wyj
 * @since 2025-10-29
 */
@Service
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements IOrderService {

}
