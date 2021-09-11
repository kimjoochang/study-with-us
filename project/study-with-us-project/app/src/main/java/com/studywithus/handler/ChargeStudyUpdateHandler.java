package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.util.Prompt;

public class ChargeStudyUpdateHandler extends AbstractChargeStudyHandler{

	public ChargeStudyUpdateHandler(List<ChargeStudy> chargeStudyList) {
		super(chargeStudyList);	
	}

	// 유료 스터디 수정
	@Override
	public void execute() {
		System.out.println("[유료 스터디 / 수정]");
		int no = Prompt.inputInt("번호? ");

		ChargeStudy study = findByNo(no);

		if (study == null) {
			System.out.println();
			System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
			return;
		}

		String title = Prompt.inputString(String.format("[%s] 유료 스터디 / 수정된 스터디 제목: ", study.getTitle()));
		String explanation = Prompt.inputString(String.format("[%s] 유료 스터디 / 수정된 내용: ", study.getExplanation()));

		String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println();
			System.out.println("유료 스터디 수정을 취소하였습니다.\n");
			return;
		}

		study.setTitle(title);
		study.setExplanation(explanation);
		System.out.println();
		System.out.println("유료 스터디를 수정하였습니다.\n");
	}

}
