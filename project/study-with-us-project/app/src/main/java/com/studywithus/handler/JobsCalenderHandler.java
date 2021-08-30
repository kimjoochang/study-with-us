package com.studywithus.handler;

import com.studywithus.domain.Calender;
import com.studywithus.util.Prompt;

public class JobsCalenderHandler {

  static final int MAX_LENGTH = 5;

  Calender[] calenders = new Calender[MAX_LENGTH];
  int size = 0;

  // 이달의 채용공고 생성
  public void add() {
    System.out.println("[이달의 채용공고 등록]");

    Calender calender = new Calender();

    calender.setNo(Prompt.inputInt("번호 "));
    calender.setTitle(Prompt.inputString("제목 "));
    calender.setContent(Prompt.inputString("내용 "));
    calender.setStartDate(Prompt.inputDate("시작일 "));
    calender.setEndDate(Prompt.inputDate("종료일 "));
    calender.setEndDate(Prompt.inputDate("메모 "));

    JobsCalenderHandler[] calenders = new JobsCalenderHandler[MAX_LENGTH];
  }

  // 이달의 채용공고 상세목록
  public void detail() {
    System.out.println("[이달의 채용공고 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Calender calender = findByNo(no);

    if (calender == null) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", calender.getTitle());
    System.out.printf("내용: %s\n", calender.getContent());
    System.out.printf("시작일: %s\n", calender.getStartDate());
    System.out.printf("종료일: %s\n", calender.getEndDate());
    System.out.printf("메모: %s\n", calender.getMemo());
  }

  // 이달의 채용공고 변경
  public void update() {
    System.out.println("[이달의 채용공고 변경]");
    int no = Prompt.inputInt("번호? ");

    Calender calender = findByNo(no);

    if (calender == null) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s)? ", calender.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)? ", calender.getContent()));
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("채용공고 변경을 취소하였습니다.");
      return;
    }

    calender.setTitle(title);
    calender.setContent(content);
    System.out.println("채용 공고를 변경하였습니다.");
  }

  // 이달의 채용공고 삭제
  public void delete() {
    System.out.println("[이달의 채용공고 삭제]");

    int no = Prompt.inputInt("번호? ");
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("채용공고 삭제를 취소하였습니다.");
      return;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.calenders[i - 1] = this.calenders[i];
    }

    this.calenders[--this.size] = null;
    System.out.println("이달의 채용공고를 삭제하였습니다.");
  }

  // 이달의 채용공고 번호 조회
  private Calender findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.calenders[i].getNo() == no) {
        return this.calenders[i];
      }
    }
    return null;
  }

  // 이달의 채용공고 조회
  private int indexOf(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.calenders[i].getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
