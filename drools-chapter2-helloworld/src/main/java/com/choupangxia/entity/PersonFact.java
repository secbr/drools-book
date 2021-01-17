package com.choupangxia.entity;

/**
 * @author sec
 * @version 1.0
 * @date 2021/1/18
 **/
public class PersonFact {

	/**
	 * 年龄
	 */
	private int age;

	/**
	 * 得分
	 */
	private int score;

	/**
	 * 结果描述
	 */
	private String desc;
	// 省略getter/setter

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
