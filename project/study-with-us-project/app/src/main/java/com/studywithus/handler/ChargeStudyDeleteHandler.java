package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.util.Prompt;

public class ChargeStudyDeleteHandler extends AbstractChargeStudyHandler{

	public ChargeStudyDeleteHandler(List<ChargeStudy> chargeStudyList) {
		super(chargeStudyList);	
	}

	// 유료 스터디 삭제 요청
	@Override
	public void execute() {
		System.out.println("[유료 스터디 / 삭제 요청]");
		int no = Prompt.inputInt("번호? ");

		ChargeStudy chargeStudy = findByNo(no);

		if (chargeStudy == null) {
			System.out.println();
			System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
			return;
		}

		String input = Prompt.inputString("정말 삭제 요청 하시겠습니까? (y/N) ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println();
			System.out.println("유료 스터디 삭제 요청을 취소하였습니다.\n");
			return;
		}
	}

}
