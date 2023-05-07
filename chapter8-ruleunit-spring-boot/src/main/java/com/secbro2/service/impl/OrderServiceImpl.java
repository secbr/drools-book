package com.secbro2.service.impl;

import com.secbro2.entity.GoodsOrder;
import com.secbro2.service.OrderService;
import com.secbro2.unit.GoodsOrderUnit;
import org.drools.ruleunits.api.RuleUnitInstance;
import org.drools.ruleunits.api.RuleUnitProvider;
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
	private RuleUnitProvider ruleUnitProvider;

	@Override
	public long getDiscountAmt(GoodsOrder goodsOrder) {

		GoodsOrderUnit unit = new GoodsOrderUnit();
		try (RuleUnitInstance<GoodsOrderUnit> instance = ruleUnitProvider.createRuleUnitInstance(unit)) {
			unit.getGoodsOrders().add(goodsOrder);
			int count = instance.fire();
			LOGGER.info("触发了{}条规则", count);
			if (SUCCESS_CODE.equals(goodsOrder.getCode())) {
				return goodsOrder.getAmount();
			}
		}

		LOGGER.warn("业务逻辑异常,code={},msg={}", goodsOrder.getCode(), goodsOrder.getMsg());
		throw new RuntimeException("规则处理异常");
	}
}
