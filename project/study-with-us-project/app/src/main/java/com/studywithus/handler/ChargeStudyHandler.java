package src.main.java.com.studywithus.handler;
package main.java.com.studywithus.handler;

import java.sql.Date;
import main.java.com.studywithus.domain.Board;
import main.java.com.studywithus.util.Prompt;

public class ChargeStudyHandler {

	// 모든 게시판의 최대 배열 개수가 같기 때문에 다음 변수는 
	// 그냥 static 필드로 남겨둔다.
	static final int MAX_LENGTH = 5;

	// 게시판 마다 따로 관리해야 하기 때문에 인스턴스 필드로 전환한다.
	// => static 옵션을 뺀다.
	Board[] studys = new Board[MAX_LENGTH];
	int size = 0;

	// 유료스터디 생성
	public void add() {
		System.out.println("[새 유료스터디]");

		Board study = new Board();

		study.no = Prompt.inputInt("번호? ");
		study.title = Prompt.inputString("제목? ");
		study.content = Prompt.inputString("내용? ");
		study.writer = Prompt.inputString("멘토? ");
		study.registeredDate = new Date(System.currentTimeMillis());
		//    board.viewCount = 0; // 인스턴스 변수는 생성되는 순간 기본 값이 0으로 설정된다.

		this.studys[this.size++] = study;
	}

	// 유료스터디 목록
	public void list() {
		System.out.println("[유료스터디 목록]");
		for (int i = 0; i < this.size; i++) {
			System.out.printf("%d, %s, %s, %s, %d, %d\n", 
					this.studys[i].no, 
					this.studys[i].title, 
					this.studys[i].writer,
					this.studys[i].registeredDate,
					this.studys[i].viewCount, 
					this.studys[i].like);
		}
	}

	// 유료스터디 상세보기
	public void detail() {
		System.out.println("[유료스터디 상세보기]");
		int no = Prompt.inputInt("번호? ");

		Board study = findByNo(no);

		if (study == null) {
			System.out.println("해당 번호의 유료스터디가 없습니다.");
			return;
		}

		System.out.printf("제목: %s\n", study.title);
		System.out.printf("내용: %s\n", study.content);
		System.out.printf("작성자: %s\n", study.writer);
		System.out.printf("등록일: %s\n", study.registeredDate);
		System.out.printf("조회수: %d\n", ++study.viewCount);
	}

	// 유료스터디 변경
	public void update() {
		System.out.println("[유료스터디 변경]");
		int no = Prompt.inputInt("번호? ");

		Board study = findByNo(no);

		if (study == null) {
			System.out.println("해당 번호의 유료스터디가 없습니다.");
			return;
		}

		String title = Prompt.inputString(String.format("제목(%s)? ", study.title));
		String content = Prompt.inputString(String.format("내용(%s)? ", study.content));

		String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("유료스터디 변경을 취소하였습니다.");
			return;
		}

		study.title = title;
		study.content = content;
		System.out.println("유료스터디 변경하였습니다.");
	}

	// 유료스터디 삭제요청
	public void delete() {
		System.out.println("[유료스터디 삭제요청]");
		int no = Prompt.inputInt("번호? ");

		int index = indexOf(no);

		if (index == -1) {
			System.out.println("해당 번호의 유료스터디가 없습니다.");
			return;
		}

		String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("유료스터디 삭제를 요청 하셨습니다.");
			return;
		}
	}

	// 삭제요청된 유료스터디 조회
	public void deletedDetail() {
		System.out.println("[삭제요청된 유료스터디 상세보기]");
		int no = Prompt.inputInt("번호? ");

		Board study = findByNo(no);

		if (study == null) {
			System.out.println("해당 번호의 삭제요청된 유료스터디가 없습니다.");
			return;
		}

		System.out.printf("제목: %s\n", study.title);
		System.out.printf("내용: %s\n", study.content);
		System.out.printf("작성자: %s\n", study.writer);
		System.out.printf("등록일: %s\n", study.registeredDate);
		System.out.printf("조회수: %d\n", ++study.viewCount);
	}

	// 유료스터디 조회 (변경)
	private Board findByNo(int no) {
		for (int i = 0; i < this.size; i++) {
			if (this.studys[i].no == no) {
				return this.studys[i];
			}
		}
		return null;
	}

	// 유료스터디 조회 (삭제)
	private int indexOf(int no) {
		for (int i = 0; i < this.size; i++) {
			if (this.studys[i].no == no) {
				return i;
			}
		}
		return -1;
	}

	// 모든 게시판의 최대 배열 개수가 같기 때문에 다음 변수는 
	// 그냥 static 필드로 남겨둔다.
	static final int MAX_LENGTH = 5;

	// 게시판 마다 따로 관리해야 하기 때문에 인스턴스 필드로 전환한다.
	// => static 옵션을 뺀다.
	Board[] studys = new Board[MAX_LENGTH];
	int size = 0;

	// 유료스터디 생성
	public void add() {
		System.out.println("[새 유료스터디]");

		Board study = new Board();

		study.no = Prompt.inputInt("번호? ");
		study.title = Prompt.inputString("제목? ");
		study.content = Prompt.inputString("내용? ");
		study.writer = Prompt.inputString("멘토? ");
		study.registeredDate = new Date(System.currentTimeMillis());
		//    board.viewCount = 0; // 인스턴스 변수는 생성되는 순간 기본 값이 0으로 설정된다.

		this.studys[this.size++] = study;
	}

	// 유료스터디 목록
	public void list() {
		System.out.println("[유료스터디 목록]");
		for (int i = 0; i < this.size; i++) {
			System.out.printf("%d, %s, %s, %s, %d, %d\n", 
					this.studys[i].no, 
					this.studys[i].title, 
					this.studys[i].writer,
					this.studys[i].registeredDate,
					this.studys[i].viewCount, 
					this.studys[i].like);
		}
	}

	// 유료스터디 상세보기
	public void detail() {
		System.out.println("[유료스터디 상세보기]");
		int no = Prompt.inputInt("번호? ");

		Board study = findByNo(no);

		if (study == null) {
			System.out.println("해당 번호의 게시글이 없습니다.");
			return;
		}

		System.out.printf("제목: %s\n", study.title);
		System.out.printf("내용: %s\n", study.content);
		System.out.printf("작성자: %s\n", study.writer);
		System.out.printf("등록일: %s\n", study.registeredDate);
		System.out.printf("조회수: %d\n", ++study.viewCount);
	}

	// 유료스터디 변경
	public void update() {
		System.out.println("[유료스터디 변경]");
		int no = Prompt.inputInt("번호? ");

		Board study = findByNo(no);

		if (study == null) {
			System.out.println("해당 번호의 유료스터디가 없습니다.");
			return;
		}

		String title = Prompt.inputString(String.format("제목(%s)? ", study.title));
		String content = Prompt.inputString(String.format("내용(%s)? ", study.content));

		String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("유료스터디 변경을 취소하였습니다.");
			return;
		}

		study.title = title;
		study.content = content;
		System.out.println("유료스터디 변경하였습니다.");
	}

	// 유료스터디 삭제요청
	public void delete() {
		System.out.println("[유료스터디 삭제요청]");
		int no = Prompt.inputInt("번호? ");

		int index = indexOf(no);

		if (index == -1) {
			System.out.println("해당 번호의 유료스터디가 없습니다.");
			return;
		}

		String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
		if (input.equalsIgnoreCase("n") || input.length() == 0) {
			System.out.println("유료스터디 삭제를 요청 하셨습니다.");
			return;
		}
	}

	// 삭제요청된 유료스터디 조회
	public void deletedDetail() {
		System.out.println("[삭제요청된 유료스터디 상세보기]");
		int no = Prompt.inputInt("번호? ");

		Board study = findByNo(no);

		if (study == null) {
			System.out.println("해당 번호의 삭제요청된 유료스터디가 없습니다.");
			return;
		}

		System.out.printf("제목: %s\n", study.title);
		System.out.printf("내용: %s\n", study.content);
		System.out.printf("작성자: %s\n", study.writer);
		System.out.printf("등록일: %s\n", study.registeredDate);
		System.out.printf("조회수: %d\n", ++study.viewCount);
	}

	// 유료스터디 조회 (변경)
	private Board findByNo(int no) {
		for (int i = 0; i < this.size; i++) {
			if (this.studys[i].no == no) {
				return this.studys[i];
			}
		}
		return null;
	}

	// 유료스터디 조회 (삭제)
	private int indexOf(int no) {
		for (int i = 0; i < this.size; i++) {
			if (this.studys[i].no == no) {
				return i;
			}
		}
		return -1;
	}

}

