package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.Member;

public class AuthUserInfoHandler implements Command {

	public AuthUserInfoHandler(List<Member> memberList2) {
	}

	@Override
	public void execute() {
		System.out.println("[내정보]");

		Member loginUser = AuthLoginHandler.getLoginUser();

		if (loginUser == null) {
			System.out.println("로그인 하지 않았습니다.");
			return;
		}

		System.out.printf("이름: %s\n", loginUser.getName());
		System.out.printf("이메일: %s\n", loginUser.getEmail());
		System.out.printf("휴대폰 번호: %s\n", loginUser.getPhoneNumber());
		System.out.printf("가입일: %s\n", loginUser.getRegisteredDate());
	}
}
