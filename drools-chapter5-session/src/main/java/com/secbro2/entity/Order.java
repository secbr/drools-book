package com.secbro2.entity;

/**
 * @author sec
 * @version 1.0
 * @date 2021/6/8
 **/
public class Order {

	private String orderNo;

	private double amount;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public double getAmount() {
		return amount;
	}

	public void setAmount(double amount) {
		this.amount = amount;
	}
}
