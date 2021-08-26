package com.studywithus.handler;

import java.sql.Date;

import com.studywithus.domain.Board;
import com.studywithus.util.Prompt;

public class FreeStudyHandler {

	static final int MAX_LENGTH = 5;

	Board[] studys = new Board[MAX_LENGTH];
	int size = 0;

	// 무료 스터디 생성
	public void add() {
		System.out.println("[새 무료 스터디]");

		Board study = new Board();

		study.no = Prompt.inputInt("번호? ");
		study.title = Prompt.inputString("제목? ");
		study.content = Prompt.inputString("내용? ");
		study.writer = Prompt.inputString("팀장? ");
		study.registeredDate = new Date(System.currentTimeMillis());

		this.studys[this.size++] = study;
	}

	// 무료 스터디 목록
	public void list() {
		System.out.println("[무료 스터디 목록]");
		for (int i = 0; i < this.size; i++) {
			System.out.printf("%d, %s, %s, %s, %d, %d\n", this.studys[i].no, this.studys[i].title, this.studys[i].writer,
					this.studys[i].registeredDate, this.studys[i].viewCount, this.studys[i].like);
		}
	}

	// 무료 스터디 상세보기
	public void detail() {
		System.out.println("[무료 스터디 상세보기]");
		int no = Prompt.inputInt("번호? ");

		Board board = findByNo(no);

		if (board == null) {
			System.out.println("해당 번호의 무료 스터디가 없습니다.");
			return;
		}

		System.out.printf("제목: %s\n", board.title);
		System.out.printf("내용: %s\n", board.content);
		System.out.printf("팀장: %s\n", board.writer);
		System.out.printf("등록일: %s\n", board.registeredDate);
		System.out.printf("조회수: %d\n", ++board.viewCount);
	}

	// 무료 스터디 변경
	public void update() {
		System.out.println("[무료 스터디 변경]");
		int no = Prompt.inputInt("번호? ");

		Board board = findByNo(no);

		if (board == null) {
			System.out.println("해당 번호의 무료 스터디가 없습니다.");
			return;
		}

		String title = Prompt.inputString(String.format("[%s] 수정된 제목: ", board.title));
		String content = Prompt.inputString(String.format("[%s] 수정된 내용: ", board.content));

		String input = Prompt.inputString("정말 변경하시겠습니까? (y/N) ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("무료 스터디 변경을 취소하였습니다.");
			return;
		}

		board.title = title;
		board.content = content;
		System.out.println("무료 스터디를 변경하였습니다.");
	}

	// 무료 스터디 삭제
	public void delete() {
		System.out.println("[무료 스터디 삭제]");
		int no = Prompt.inputInt("번호? ");

		int index = indexOf(no);

		if (index == -1) {
			System.out.println("해당 번호의 무료 스터디가 없습니다.");
			return;
		}

		String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("무료 스터디 삭제를 취소하였습니다.");
			return;
		}

		for (int i = index + 1; i < this.size; i++) {
			this.studys[i - 1] = this.studys[i];
		}
		this.studys[--this.size] = null;

		System.out.println("무료 스터디를 삭제하였습니다.");
	}

	// 무료 스터디 번호 조회
	private Board findByNo(int no) {
		for (int i = 0; i < this.size; i++) {
			if (this.studys[i].no == no) {
				return this.studys[i];
			}
		}
		return null;
	}

	// 무료 스터디 조회
	private int indexOf(int no) {
		for (int i = 0; i < this.size; i++) {
			if (this.studys[i].no == no) {
				return i;
			}
		}
		return -1;
	}
}