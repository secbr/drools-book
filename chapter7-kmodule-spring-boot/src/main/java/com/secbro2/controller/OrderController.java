package com.secbro2.controller;

import com.secbro2.entity.GoodsOrder;
import com.secbro2.service.OrderService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @author sec
 * @version 1.0
 * @date 2023/2/5
 **/
@RestController
@RequestMapping("/order")
public class OrderController {

	@Resource
	private OrderService orderService;

	@GetMapping("/calcAmt")
	public long calcDiscountAmt(long amount) {
		GoodsOrder order = new GoodsOrder();
		// 暂时随机生成
		order.setOrderNo(getOrderNo());
		order.setAmount(amount);
		return orderService.getDiscountAmt(order);
	}

	private String getOrderNo() {
		// 简单示意，根据业务需要自定义实现
		return "N" + System.currentTimeMillis();
	}
}
