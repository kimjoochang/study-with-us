package com.studywithus.handler.user;

import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class MembershipWithdrawalHandler implements Command {

	RequestAgent requestAgent;

	public MembershipWithdrawalHandler(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[회원 탈퇴]\n");

		System.out.println("회원 탈퇴를 위해 회원 정보를 다시 한 번 입력해주세요.\n");

		String email = Prompt.inputString("이메일: ");
		String password = Prompt.inputString("비밀번호: ");

		// Member member = findByEmailPassword(email, password);
		Member loginUser = AuthLogInHandler.getLoginUser();

		//		HashMap<String, String> params = new HashMap<>();
		//		params.put("email", email);
		//		params.put("password", password);
		//
		//		requestAgent.request("member.selectOneByEmailPassword", params);

		if (!loginUser.getEmail().equals(email)) {
			System.out.println();
			System.out.println("현재 로그인한 회원 정보와 일치하지 않습니다.\n");
			return;
		} else if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			System.out.println();
			System.out.println("아이디와 비밀번호가 일치하는 회원을 찾을 수 없습니다.\n");
			return;
		} else {
			System.out.println();
			String input = Prompt.inputString("정말 회원 탈퇴하시겠습니까? (y/N) ");

			if (input.equalsIgnoreCase("n") || input.length() == 0) {
				System.out.println();
				System.out.println(" 회원 탈퇴가 취소되었습니다.\n");
			} else {

				requestAgent.request("member.delete", AuthLogInHandler.getLoginUser().getEmail());

				AuthLogInHandler.userAccessLevel = Menu.ACCESS_LOGOUT;
				System.out.println();
				System.out.println("회원 탈퇴가 완료되었습니다.\n");
				return;
			}
		}

	}

	// 기존 코드
	// private Member findById() {
	// for (Member member : memberList) {
	// if (member.getId().equalsIgnoreCase(AuthLogInHandler.getLoginUser().getId()))
	// {
	// return member;
	// }
	// return null;
	// }
	// return null;
	// }

	// 선영 추가 코드
	// private Member findByEmailPassword(String email, String password) {
	// Collection<Member> memberList = requestAgent.getObjects(Member.class);
	//
	// for (Member member : memberList) {
	// if (member.getEmail().equalsIgnoreCase(email) &&
	// member.getPassword().equals(password)) {
	// return member;
	// }
	// }
	// return null;
	// }
}
