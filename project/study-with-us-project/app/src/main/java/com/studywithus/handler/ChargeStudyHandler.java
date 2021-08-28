package com.studywithus.handler;

import java.sql.Date;

import com.studywithus.domain.Board;
import com.studywithus.util.Prompt;

public class ChargeStudyHandler {

	static final int MAX_LENGTH = 5;

	Board[] studies = new Board[MAX_LENGTH];
	int size = 0;

	// 유료 스터디 생성
	public void add() {
		System.out.println("[새 유료 스터디]");

		Board study = new Board();

		study.no = Prompt.inputInt("번호? ");
		study.title = Prompt.inputString("스터디 제목? ");
		study.explanation = Prompt.inputString("스터디 설명? ");
		study.writer = Prompt.inputString("멘토? ");
		study.area = Prompt.inputString("지역? ");
		study.registeredDate = new Date(System.currentTimeMillis());

		this.studies[this.size++] = study;
	}

	// 유료 스터디 조회
	public void list() {
		System.out.println("[유료 스터디 조회]");
		for (int i = 0; i < this.size; i++) {
			System.out.printf("%d, %s, %s, %s, %d, %d\n", this.studies[i].no, this.studies[i].title,
					this.studies[i].writer, this.studies[i].registeredDate, this.studies[i].viewCount,
					this.studies[i].like);
		}
	}

	// 유료 스터디 상세보기
	public void detail() {
		System.out.println("[유료 스터디 상세보기]");
		int no = Prompt.inputInt("번호? ");

		Board study = findByNo(no);

		if (study == null) {
			System.out.println("해당 번호의 유료 스터디가 없습니다.");
			return;
		}

		System.out.printf("스터디 제목: %s\n", study.title);
		System.out.printf("스터디 설명: %s\n", study.explanation);
		System.out.printf("멘토: %s\n", study.writer);
		System.out.printf("등록일: %s\n", study.registeredDate);
		System.out.printf("조회수: %d\n", ++study.viewCount);
	}

	// 유료 스터디 변경
	public void update() {
		System.out.println("[유료 스터디 변경]");
		int no = Prompt.inputInt("번호? ");

		Board study = findByNo(no);

		if (study == null) {
			System.out.println("해당 번호의 유료 스터디가 없습니다.");
			return;
		}

		String title = Prompt.inputString(String.format("[%s] 수정된 스터디 제목: ", study.title));
		String explanation = Prompt.inputString(String.format("[%s] 수정된 내용: ", study.explanation));

		String input = Prompt.inputString("정말 변경하시겠습니까? (y/N) ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("유료 스터디 변경을 취소하였습니다.");
			return;
		}

		study.title = title;
		study.explanation = explanation;
		System.out.println("유료 스터디를 변경하였습니다.");
	}

	// 유료 스터디 삭제 요청
	public void delete() {
		System.out.println("[유료 스터디 삭제 요청]");
		int no = Prompt.inputInt("번호? ");

		int index = indexOf(no);

		if (index == -1) {
			System.out.println("해당 번호의 유료 스터디가 없습니다.");
			return;
		}

		String input = Prompt.inputString("정말 삭제 요청 하시겠습니까? (y/N) ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("유료 스터디 삭제 요청을 취소하였습니다.");
			return;
		}
	}

	// 삭제 요청된 유료 스터디 상세보기
	public void deletedDetail() {
		System.out.println("[삭제 요청된 유료 스터디 상세보기]");
		int no = Prompt.inputInt("번호? ");

		Board study = findByNo(no);

		if (study == null) {
			System.out.println("해당 번호의 삭제 요청 유료 스터디가 없습니다.");
			return;
		}

		System.out.printf("스터디 제목: %s\n", study.title);
		System.out.printf("스터디 설명: %s\n", study.explanation);
		System.out.printf("멘토: %s\n", study.writer);
		System.out.printf("등록일: %s\n", study.registeredDate);
		System.out.printf("조회수: %d\n", ++study.viewCount);
	}

	// 유료 스터디 번호 조회
	private Board findByNo(int no) {
		for (int i = 0; i < this.size; i++) {
			if (this.studies[i].no == no) {
				return this.studies[i];
			}
		}
		return null;
	}

	// 유료 스터디 조회
	private int indexOf(int no) {
		for (int i = 0; i < this.size; i++) {
			if (this.studies[i].no == no) {
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














