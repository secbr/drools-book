package com.secbro2.entity;

/**
 * @author sec
 * @version 1.0
 * @date 2021/3/21
 **/
public class DroolsResult {

	/**
	 * 规则执行结果。ACCESSED：通过、REFUSED：拒绝；
	 */
	private String code = "ACCESSED";

	/**
	 * 规则结果描述
	 */
	private String desc;

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
}
