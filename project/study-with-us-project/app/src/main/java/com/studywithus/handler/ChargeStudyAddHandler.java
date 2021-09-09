package com.studywithus.handler;

import java.sql.Date;
import java.util.List;

import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class ChargeStudyAddHandler extends AbstractChargeStudyHandler{

	public ChargeStudyAddHandler(List<Study> chargeStudyList) {
		super(chargeStudyList);	

		Study mentorTestUser = new Study();

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

		Study study = new Study();

		study.setNo(Prompt.inputInt("번호? "));
		study.setWriter(Prompt.inputString("멘토? "));
		study.setArea(Prompt.inputString("지역? "));
		study.setTitle(Prompt.inputString("스터디 제목? "));
		study.setExplanation(Prompt.inputString("스터디 설명? "));
		study.setPrice(Prompt.inputInt("가격? " ));
		study.setRegisteredDate(new Date(System.currentTimeMillis()));
		chargeStudyList.add(study);

		System.out.println();
		System.out.println("유료스터디 등록이 완료되었습니다.\n");
	}
}
