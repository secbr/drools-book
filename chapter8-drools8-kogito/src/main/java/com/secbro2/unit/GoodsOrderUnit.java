package com.secbro2.unit;

import com.secbro2.entity.GoodsOrder;
import org.drools.ruleunits.api.DataSource;
import org.drools.ruleunits.api.DataStore;
import org.drools.ruleunits.api.RuleUnitData;

/**
 * @author sec
 * @version 1.0
 * @date 2023/5/6
 **/
public class GoodsOrderUnit implements RuleUnitData {

	private DataStore<GoodsOrder> goodsOrders;

	public GoodsOrderUnit() {
		this(DataSource.createStore());
	}

	public GoodsOrderUnit(DataStore<GoodsOrder> goodsOrders) {
		this.goodsOrders = goodsOrders;
	}

	public DataStore<GoodsOrder> getGoodsOrders() {
		return goodsOrders;
	}

	public void setGoodsOrders(DataStore<GoodsOrder> goodsOrders) {
		this.goodsOrders = goodsOrders;
	}

}
