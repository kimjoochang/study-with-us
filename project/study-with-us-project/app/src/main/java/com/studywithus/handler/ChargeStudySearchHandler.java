package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.util.Prompt;

public class ChargeStudySearchHandler extends AbstractChargeStudyHandler{

	public ChargeStudySearchHandler(List<ChargeStudy> chargeStudyList) {
		super(chargeStudyList);	
	}

	// 유료 스터디 검색
	@Override
	public void execute() {
		System.out.println("[유료스터디 / 검색]");

		String input = Prompt.inputString("검색어? ");

		for (ChargeStudy chargeStudy : chargeStudyList) {
			if (!chargeStudy.getTitle().contains(input) &&
					!chargeStudy.getExplanation().contains(input) &&
					!chargeStudy.getWriter().contains(input)) {
				continue;
			}

			System.out.printf("%d, %s, %s, %s, %d, %d\n", 
					chargeStudy.getNo(), 
					chargeStudy.getTitle(), 
					chargeStudy.getWriter(),
					chargeStudy.getRegisteredDate(),
					chargeStudy.getViewCount(), 
					chargeStudy.getLike());
		}
	}
}
