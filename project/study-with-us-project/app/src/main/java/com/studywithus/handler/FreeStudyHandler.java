package com.studywithus.handler;

import java.sql.Date;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyHandler {

  static final int MAX_LENGTH = 5;

  FreeStudy[] studies = new FreeStudy[MAX_LENGTH];
  int size = 0;

  // 무료 스터디 생성
  public void add() {
    System.out.println("[새 무료 스터디]");

    FreeStudy study = new FreeStudy();

    study.setNo(Prompt.inputInt("번호? "));
    study.setWriter(Prompt.inputString("팀장? "));

    System.out.println("온/오프라인?");
    System.out.println("1. 온라인");
    System.out.println("2. 오프라인");
    study.setOnOffLine(Prompt.inputString("> "));

    if (study.getOnOffLine().equals("2")) {
      study.setArea(Prompt.inputString("지역? "));
    }

    study.setTitle(Prompt.inputString("제목? "));
    study.setExplanation(Prompt.inputString("설명? "));
    study.setRule(Prompt.inputString("규칙? "));
    study.setRegisteredDate(new Date(System.currentTimeMillis()));

    this.studies[this.size++] = study;
    System.out.println("무료 스터디 등록이 완료되었습니다.");
  }

  // 무료 스터디 신청
  public void apply() {
    System.out.println("[무료 스터디 신청]");
  }

  // 무료 스터디 조회
  public void list() {
    System.out.println("[무료 스터디 조회]");

    for (int i = 0; i < this.size; i++) {
      System.out.printf("%d, %s, %s, %s, %d\n", this.studies[i].getNo(), this.studies[i].getTitle(), this.studies[i].getWriter(),
          this.studies[i].getRegisteredDate(), this.studies[i].getViewCount());
    }
  }

  // 무료 스터디 상세보기
  public void detail() {
    System.out.println("[무료 스터디 상세보기]");

    int no = Prompt.inputInt("번호? ");
    FreeStudy study = findByNo(no);

    if (study == null) {
      System.out.println("해당 번호의 무료 스터디가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", study.getTitle());
    System.out.printf("내용: %s\n", study.getExplanation());
    System.out.printf("팀장: %s\n", study.getWriter());

    if (study.getArea().length() != 0) {
      System.out.printf("지역: %s\n", study.getArea());
    }

    System.out.printf("설명: %s\n", study.getExplanation());
    System.out.printf("규칙: %s\n", study.getRule());
    System.out.printf("등록일: %s\n", study.getRegisteredDate());
    System.out.printf("조회수: %d\n", study.getViewCount() + 1);
  }

  // 무료 스터디 변경
  public void update() {
    System.out.println("[무료 스터디 변경]");

    int no = Prompt.inputInt("번호? ");

    FreeStudy study = findByNo(no);

    if (study == null) {
      System.out.println("해당 번호의 무료 스터디가 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", study.getTitle()));
    String explanation = Prompt.inputString(String.format("[%s] 수정할 설명: ", study.getExplanation()));
    String rule = Prompt.inputString(String.format("[%s] 수정할 규칙: ", study.getRule()));

    String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 수정을 취소하였습니다.");
      return;
    }

    study.setTitle(title);
    study.setExplanation(explanation);
    study.setRule(rule);

    System.out.println("무료 스터디를 수정하였습니다.");
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

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

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
  private FreeStudy findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.studies[i].getNo() == no) {
        return this.studies[i];
      }
    }
    return null;
  }

  // 무료 스터디 조회
  private int indexOf(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.studies[i].getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
