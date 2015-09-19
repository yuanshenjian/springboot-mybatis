package org.yood.springboot.mybatis.entity;

import java.io.Serializable;

public class Contact implements Serializable {

	private int id;
	private int userId;
	private String username;
	private String address;
	private int age;

	public Contact() {
	}

	public Contact(String name, String address, int age) {
		this.username = name;
		this.address = address;
		this.age = age;
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getUsername() {
		return username;
	}
	public void setUsername(String username) {
		this.username = username;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public int getAge() {
		return age;
	}
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public void setAge(int age) {
		this.age = age;
	}

	@Override
	public String toString() {
		return "Contact{" +
				"id=" + id +
				", userId=" + userId +
				", username='" + username + '\'' +
				", address='" + address + '\'' +
				", age=" + age +
				'}';
	}
}
