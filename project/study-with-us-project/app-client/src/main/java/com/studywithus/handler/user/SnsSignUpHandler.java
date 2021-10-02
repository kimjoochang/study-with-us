package com.studywithus.handler.user;

import java.sql.Date;

import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class SnsSignUpHandler implements Command {

	RequestAgent requestAgent;

	public SnsSignUpHandler(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {

		Member member = new Member();

		String name = Prompt.inputString("이름을 입력하세요. > ");
		String snsEmail = Prompt.inputString("연동할 SNS 계정을 입력하세요. > ");
		String password = Prompt.inputString("SNS 계정의 비밀번호를 입력하세요. > ");

		System.out.println();

		// snsId = findBySnsId(snsId);

		if (snsEmail == null) {
			System.out.println("이미 연동된 SNS 계정입니다.\n");

		} else if (!snsEmail.contains("@") || !snsEmail.contains(".com")) {
			System.out.println("이메일 형식의 SNS 계정을 입력하세요.\n");

		} else {
			member.setName(name);
			member.setEmail(snsEmail);
			member.setPassword(password);
			member.setRegisteredDate((new Date(System.currentTimeMillis())));
			member.setUserAccessLevel(Menu.ACCESS_GENERAL);
			member.setRegisteredDate(new Date(System.currentTimeMillis()));

			requestAgent.request("member.insert", member);

			System.out.println();
			System.out.println("SNS 계정 연동이 완료되었습니다.\n");
		}
	}

	// private String findBySnsId(String snsId) {
	// for (Member member : memberList) {
	// if (snsId.equalsIgnoreCase(member.getId())) {
	// return null;
	// }
	// }
	// return snsId;
	// }
}
