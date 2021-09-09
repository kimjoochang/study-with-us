package com.studywithus.handler;

import java.sql.Date;
import java.util.List;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.util.Prompt;

public class ChargeStudyAddHandler extends AbstractChargeStudyHandler{

	public ChargeStudyAddHandler(List<ChargeStudy> chargeStudyList) {
		super(chargeStudyList);	

		ChargeStudy mentorTestUser = new ChargeStudy();

		mentorTestUser.setNo(1);
		mentorTestUser.setWriter("");
		mentorTestUser.setArea("서울시 강남구");
		mentorTestUser.setTitle("[회화] 20년 서울 토박이 LA원어민 따라잡기");
		mentorTestUser.setExplanation("Array / Linked List / Stack");
		mentorTestUser.setPrice(100000);
		mentorTestUser.setRegisteredDate(new Date(System.currentTimeMillis()));


	}

	// 멘토 테스트 유저 생성


	// 유료 스터디 생성
	@Override
	public void execute() {
		System.out.println("[유료 스터디 / 생성]\n");

		ChargeStudy chargeStudy = new ChargeStudy();

		chargeStudy.setNo(Prompt.inputInt("번호? "));
		chargeStudy.setWriter(Prompt.inputString("멘토? "));
		chargeStudy.setArea(Prompt.inputString("지역? "));
		chargeStudy.setTitle(Prompt.inputString("스터디 제목? "));
		chargeStudy.setExplanation(Prompt.inputString("스터디 설명? "));
		chargeStudy.setPrice(Prompt.inputInt("가격? " ));
		chargeStudy.setRegisteredDate(new Date(System.currentTimeMillis()));
		chargeStudyList.add(chargeStudy);

		System.out.println();
		System.out.println("유료스터디 등록이 완료되었습니다.\n");
	}
}
