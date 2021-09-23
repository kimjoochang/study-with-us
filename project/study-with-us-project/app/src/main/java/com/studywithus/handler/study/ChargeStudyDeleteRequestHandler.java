package com.studywithus.handler.study;

import java.util.List;

import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyDeleteRequestHandler extends AbstractStudyHandler {

	Study chargeStudy;
	List<Study> chargeDeleteRequestList;

	public ChargeStudyDeleteRequestHandler(List<Study> chargeStudyList, List<Study> chargeDeleteRequestList) {
		super(chargeStudyList);
		this.chargeDeleteRequestList = chargeDeleteRequestList;
	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[유료 스터디 / 삭제 요청]\n");
		int no = (int) request.getAttribute("ChargeNo");

		Study chargeStudy = findByNo(no);

		if (chargeStudy == null) {
			System.out.println();
			System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
			return;
		}

		if (chargeStudy.getWriter().getId() != AuthLogInHandler.getLoginUser().getId()) {
			System.out.println("삭제 권한이 없습니다.");
			return;
		}

		String input = Prompt.inputString("정말 삭제 요청 하시겠습니까? (y/N) ");
		System.out.println();
		while (true) {
			if (input.equalsIgnoreCase("n") || input.length() == 0) {
				System.out.println("유료 스터디 삭제 요청을 취소하였습니다.\n");
				return;
			} else if (!input.equalsIgnoreCase("y")) {
				System.out.println("다시 입력하시오.\n");
				continue;
			} else {
				break;
			}
		}
		chargeDeleteRequestList.add(chargeStudy);
		System.out.println("삭제 요청이 완료되었습니다.\n");
	}
}
