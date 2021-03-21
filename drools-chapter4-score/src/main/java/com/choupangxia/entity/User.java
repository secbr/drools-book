package com.choupangxia.entity;

/**
 * @author sec
 * @version 1.0
 * @date 2021/3/21
 **/
public class User extends DroolsResult{

	/**
	 * 手机号
	 */
	private String phone;

	/**
	 * 固定资产，单位：万
	 */
	private int fixedAssets;

	/**
	 * 信用额度上限，单位：万
	 */
	private int maxAmount;

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public int getFixedAssets() {
		return fixedAssets;
	}

	public void setFixedAssets(int fixedAssets) {
		this.fixedAssets = fixedAssets;
	}

	public int getMaxAmount() {
		return maxAmount;
	}

	public void setMaxAmount(int maxAmount) {
		this.maxAmount = maxAmount;
	}

}
