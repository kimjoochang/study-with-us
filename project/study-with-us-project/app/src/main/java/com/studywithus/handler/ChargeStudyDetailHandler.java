package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.menuList.MenuList;
import com.studywithus.util.Prompt;

public class ChargeStudyDetailHandler extends AbstractChargeStudyHandler{

	public ChargeStudyDetailHandler(List<ChargeStudy> chargeStudyList) {
		super(chargeStudyList);	
	}

	// 유료 스터디 상세보기
	@Override
	public void execute() {
		while(true) {

			System.out.println("[유료 스터디 / 상세보기]\n");
			int no = Prompt.inputInt("번호? ");

			ChargeStudy study = findByNo(no);

			if (study == null) {
				System.out.println();
				System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
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


			System.out.printf("조회수: %d\n", study.getViewCount() + 1);

			System.out.println("------------------------------------");
			System.out.println("1. 결제하기");
			System.out.println("2. 관심목록 추가");
			System.out.println("0. 이전\n");

			int input = Prompt.inputInt("메뉴를 선택해주세요. >");
			System.out.println();
			int answer = MenuList.detailMenuList(input,study);

			if (answer == 0) {
				continue;
			} else {
				return; 
			}
		}
	}

}
