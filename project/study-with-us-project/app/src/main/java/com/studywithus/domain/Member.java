package com.studywithus.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings("serial")
public class Member implements Serializable {

	private static String adminId = "admin";
	private static String adminPassword = "admin";
	private static int userAccessLevel; // 권한
	private String name; // 회원 이름
	private String email; // 회원 이메일
	private String id; // 회원 아이디
	private String password; // 회원 비밀번호
	private String phoneNumber; // 회원 핸드폰번호
	private Date registeredDate; // 회원 가입일



	public static int getUserAccessLevel() {
		return userAccessLevel;
	}

	private List<Member> members = new ArrayList<Member>();

	//	@Override
	//	public String toString() {
	//		return "[팀장 / 멘토 = " + name + "]";
	//	}


	//  @Override
	//  public String toString() {
	//    return "Member [no=" + no + ", name=" + name + ", id=" + id + ", password=" + password + "]";
	//  }

	public static String getAdminId() {
		return adminId;
	}

	public static void setAdminId(String adminId) {
		Member.adminId = adminId;
	}

	public static String getAdminPassword() {
		return adminPassword;
	}

	public static void setAdminPassword(String adminPassword) {
		Member.adminPassword = adminPassword;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPhoneNumber() {
		return phoneNumber;
	}

	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	public Date getRegisteredDate() {
		return registeredDate;
	}

	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}
}
