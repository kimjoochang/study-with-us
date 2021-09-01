package com.studywithus.handler;

import java.sql.Date;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.menuList.MenuList;
import com.studywithus.util.Prompt;

public class ChargeStudyHandler {

	static final int MAX_LENGTH = 5;

	ChargeStudy[] studies = new ChargeStudy[MAX_LENGTH];
	int size = 0;

	// 유료 스터디 생성
	public void add() {
		System.out.println("[유료 스터디 / 생성]");

		ChargeStudy study = new ChargeStudy();

		study.setWriter(Prompt.inputString("멘토? "));
		study.setNo(Prompt.inputInt("번호? "));
		study.setArea(Prompt.inputString("지역? "));
		study.setTitle(Prompt.inputString("스터디 제목? "));
		study.setExplanation(Prompt.inputString("스터디 설명? "));
		study.setPrice(Prompt.inputInt("가격? " ));
		study.setRegisteredDate(new Date(System.currentTimeMillis()));

		this.studies[this.size++] = study;
		System.out.println();
		System.out.println("유료스터디 등록이 완료되었습니다.\n");
	}

	// 유료 스터디 조회
	public void list() {
		System.out.println("[유료 스터디 / 조회]");
		for (int i = 0; i < this.size; i++) {
			System.out.printf("%d, %s, %s, %s, %d, %d\n", this.studies[i].getNo(), this.studies[i].getTitle(),
					this.studies[i].getWriter(), this.studies[i].getRegisteredDate(), this.studies[i].getViewCount(),
					this.studies[i].getLike());
		}
	}

	// 유료 스터디 상세보기
	public void detail() {
		while(true) {

			System.out.println("[유료 스터디 / 상세보기]");
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
			System.out.println("0. 이전");
			System.out.println("1. 결제하기");
			System.out.println("2. 관심목록 추가\n");

			int input = Prompt.inputInt("메뉴를 선택해주세요");
			int answer = MenuList.detailMenuList(input);

			if (answer == 0) {
				continue;
			} else {
				return; 
			}
		}
	}

	// 유료 스터디 수정
	public void update() {
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

	// 유료 스터디 삭제 요청
	public void delete() {
		System.out.println("[유료 스터디 / 삭제 요청]");
		int no = Prompt.inputInt("번호? ");

		int index = indexOf(no);

		if (index == -1) {
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

	// 삭제 요청된 유료 스터디 상세보기
	public void deletedDetail() {
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

	// 유료 스터디 번호 조회
	private ChargeStudy findByNo(int no) {
		for (int i = 0; i < this.size; i++) {
			if (this.studies[i].getNo() == no) {
				return this.studies[i];
			}
		}
		return null;
	}

	// 유료 스터디 조회
	private int indexOf(int no) {
		for (int i = 0; i < this.size; i++) {
			if (this.studies[i].getNo() == no) {
				return i;
			}
		}
		return -1;
	}

	// 유료스터디 상세보기
	//	= > 후기 멘토소개 
	//	= > 가격 조회

	// 유료스터디 후기 작성
	//	=> 후기 삭제

	// 유료스터디 멘토 소개 조회

	// 유료스터디 결제 요청
	//	= > 유료스터디 약관 조회
}
