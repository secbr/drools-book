package com.secbro2.entity;

/**
 * @author sec
 * @version 1.0
 * @date 2023/2/5
 **/
public class GoodsOrder {

	/**
	 * 订单编号
	 */
	private String orderNo;

	/**
	 * 订单金额，单位分
	 */
	private long amount;

	/**
	 * 返回编码
	 */
	private String code = "SUCCESS";

	/**
	 * 编码描述
	 */
	private String msg;

	public String getOrderNo() {
		return orderNo;
	}

	public void setOrderNo(String orderNo) {
		this.orderNo = orderNo;
	}

	public long getAmount() {
		return amount;
	}

	public void setAmount(long amount) {
		this.amount = amount;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}
}
