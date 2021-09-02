package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.util.Prompt;

public class ChargeStudyDeletedDetailHandler extends AbstractChargeStudyHandler{

	public ChargeStudyDeletedDetailHandler(List<ChargeStudy> chargeStudyList) {
		super(chargeStudyList);	
	}

	// 삭제 요청된 유료 스터디 상세보기
	@Override
	public void execute() {
		System.out.println("[유료 스터디 / 삭제 요청된 유료 스터디 상세보기]");
		int no = Prompt.inputInt("번호? ");

		ChargeStudy study = findByNo(no);

		if (study == null) {
			System.out.println();
			System.out.println("해당 번호의 삭제 요청 유료 스터디가 없습니다.\n");
			return;
		}

		System.out.printf("스터디 제목: %s\n", study.getTitle());
		System.out.printf("스터디 설명: %s\n", study.getExplanation());
		System.out.printf("지역: %s\n", study.getArea());
		System.out.printf("멘토: %s\n", study.getWriter());
		System.out.printf("가격: %s\n", study.getPrice());
		System.out.printf("등록일: %s\n", study.getRegisteredDate());

		study.setViewCount(study.getViewCount() + 1);
		System.out.printf("조회수: %d\n", study.getViewCount());
	}

}
