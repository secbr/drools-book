package com.secbro2.service;

import com.secbro2.entity.GoodsOrder;

/**
 * @author sec
 * @version 1.0
 * @date 2023/2/5
 **/
public interface OrderService {

	long getDiscountAmt(GoodsOrder goodsOrder);
}
