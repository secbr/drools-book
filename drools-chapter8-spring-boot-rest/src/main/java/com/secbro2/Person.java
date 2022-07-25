package com.secbro2;

/**
 * @author sec
 * @version 1.0
 **/
public class Person implements java.io.Serializable {

	static final long serialVersionUID = 1L;

	private int age;

	private int score;

	private String desc;

	public Person() {
	}

	public int getAge() {
		return this.age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public int getScore() {
		return this.score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public String getDesc() {
		return this.desc;
	}

	public void setDesc(java.lang.String desc) {
		this.desc = desc;
	}

	public Person(int age, int score, java.lang.String desc) {
		this.age = age;
		this.score = score;
		this.desc = desc;
	}

}
