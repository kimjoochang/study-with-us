package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.Member;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class MembershipWithdrawalHandler extends AbstractLoginHandler {

	public MembershipWithdrawalHandler(List<Member> memberList) {
		super(memberList);
	}

	Member member = new Member();

	@Override
	public void execute() {
		System.out.println("[회원 탈퇴]");

		String id = Prompt.inputString("아이디 : ");
		String password = Prompt.inputString("비밀번호 : ");

		member = findByIdPassword(id, password);
		if (member == null) {
			System.out.println("아이디와 암호가 일치하는 회원을 찾을 수 없습니다.");
		} else {
			AuthLoginHandler.loginUser = null;
			AuthLoginHandler.userAccessLevel = Menu.ACCESS_LOGOUT;
			System.out.println("정말 회원 탈퇴하시겠습니까?");
		}
	}

	private Member findByIdPassword(String id, String password) {
		for (Member member : memberList) {
			if (member.getId().equalsIgnoreCase(id) &&
					member.getPassword().equals(password)) {
				return member;
			}
		}
		return null;
	}
}
