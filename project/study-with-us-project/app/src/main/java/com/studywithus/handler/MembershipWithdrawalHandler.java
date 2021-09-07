package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.Member;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class MembershipWithdrawalHandler extends AbstractLoginHandler {

	public MembershipWithdrawalHandler(List<Member> memberList) {
		super(memberList);
	}

	Member member = findById();

	@Override
	public void execute() {
		System.out.println("[회원 탈퇴]");

		String input = Prompt.inputString("정말 회원 탈퇴하시겠습니까?? (y/N)");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println(" 회원 탈퇴를 취소하셨습니다.");
		}

		memberList.remove(member);

		System.out.println();
		System.out.println("회원 탈퇴가 완료되었습니다.");
		userAccessLevel = Menu.ACCESS_LOGOUT;

		return;
	}

	private Member findById() {
		for (Member member : memberList) {
			if (member.getId().equalsIgnoreCase(AuthLoginHandler.getLoginUser().getId())) {
				return member;
			}
			return null;
		}
		return null;

	}
}
