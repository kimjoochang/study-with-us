package main.java.com.studywithus.handler;

import java.sql.Date;
<<<<<<< HEAD
import main.java.com.studywithus.domain.Board;
import main.java.com.studywithus.util.Prompt;
=======
import com.studywithus.domain.Board;
import com.studywithus.util.Prompt;
>>>>>>> 8940ac7e7cafc2ff59a1b9035eb847ea0211185d

public class FreeStudyHandler {

  static final int MAX_LENGTH = 5;

  Board[] studies = new Board[MAX_LENGTH];
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

    this.studies[this.size++] = study;
  }

<<<<<<< HEAD
  // 무료 스터디 목록
  public void list() {
    System.out.println("[무료 스터디 목록]");
=======
  // 무료 스터디 조회
  public void list() {
    System.out.println("[무료 스터디 조회]");
>>>>>>> 8940ac7e7cafc2ff59a1b9035eb847ea0211185d
    for (int i = 0; i < this.size; i++) {
      System.out.printf("%d, %s, %s, %s, %d, %d\n", this.studies[i].no, this.studies[i].title, this.studies[i].writer,
          this.studies[i].registeredDate, this.studies[i].viewCount, this.studies[i].like);
    }
  }

  // 무료 스터디 상세보기
  public void detail() {
    System.out.println("[무료 스터디 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Board study = findByNo(no);

    if (study == null) {
      System.out.println("해당 번호의 무료 스터디가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", study.title);
    System.out.printf("내용: %s\n", study.content);
    System.out.printf("팀장: %s\n", study.writer);
    System.out.printf("등록일: %s\n", study.registeredDate);
    System.out.printf("조회수: %d\n", ++study.viewCount);
  }

  // 무료 스터디 변경
  public void update() {
    System.out.println("[무료 스터디 변경]");
    int no = Prompt.inputInt("번호? ");

    Board study = findByNo(no);

    if (study == null) {
      System.out.println("해당 번호의 무료 스터디가 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정된 제목: ", study.title));
    String content = Prompt.inputString(String.format("[%s] 수정된 내용: ", study.content));

    String input = Prompt.inputString("정말 변경하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 변경을 취소하였습니다.");
      return;
    }

    study.title = title;
    study.content = content;
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
      this.studies[i - 1] = this.studies[i];
    }
    this.studies[--this.size] = null;

    System.out.println("무료 스터디를 삭제하였습니다.");
  }

  // 무료 스터디 번호 조회
  private Board findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.studies[i].no == no) {
        return this.studies[i];
      }
    }
    return null;
  }

  // 무료 스터디 조회
  private int indexOf(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.studies[i].no == no) {
        return i;
      }
    }
    return -1;
  }
}