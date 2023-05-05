package com.secbro2.service.impl;

import com.secbro2.entity.GoodsOrder;
import com.secbro2.service.OrderService;
import org.kie.api.runtime.KieContainer;
import org.kie.api.runtime.KieSession;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * @author sec
 * @version 1.0
 * @date 2023/2/5
 **/
@Service("orderService")
public class OrderServiceImpl implements OrderService {

	private static final Logger LOGGER = LoggerFactory.getLogger(OrderServiceImpl.class);

	private static final String SUCCESS_CODE = "SUCCESS";

	@Resource
	private KieContainer kieContainer;

	@Override
	public long getDiscountAmt(GoodsOrder goodsOrder) {
		KieSession kieSession = kieContainer.newKieSession();
		try {
			kieSession.insert(goodsOrder);
			kieSession.fireAllRules();
			if (SUCCESS_CODE.equals(goodsOrder.getCode())) {
				return goodsOrder.getAmount();
			} else {
				LOGGER.warn("业务逻辑异常,code={},msg={}", goodsOrder.getCode(), goodsOrder.getMsg());
				throw new RuntimeException("规则处理异常");
			}
		} finally {
			if (kieSession != null) {
				kieSession.dispose();
			}
		}
	}
}
