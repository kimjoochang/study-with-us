package com.studywithus.handler.study;

import java.util.List;

import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyUpdateHandler extends AbstractStudyHandler {

	public ChargeStudyUpdateHandler(List<Study> chargeStudyList) {
		super(chargeStudyList);	
	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[유료 스터디 / 수정]");
		int no = (int) request.getAttribute("no");

		Study chargeStudy = findByNo(no);

		if (chargeStudy == null) {
			System.out.println();
			System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
			return;
		} 

		if (chargeStudy.getWriter() != AuthLogInHandler.getLoginUser()) {
			System.out.println("변경 권한이 없습니다.");
			return;
		}

		String title = Prompt.inputString(String.format("[%s] 수정된 스터디 제목: ", chargeStudy.getTitle()));
		String content = Prompt.inputString(String.format("[%s] 수정된 내용: ", chargeStudy.getContent()));

		String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");

		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println();
			System.out.println("유료 스터디 수정을 취소하였습니다.\n");
			return;
		}

		chargeStudy.setTitle(title);
		chargeStudy.setContent(content);

		System.out.println();
		System.out.println("유료 스터디를 수정하였습니다.\n");
	}
}
