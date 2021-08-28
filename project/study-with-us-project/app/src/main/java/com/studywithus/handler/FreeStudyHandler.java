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

    study.writer = Prompt.inputString("팀장? ");

    System.out.println("온/오프라인?");
    System.out.println("1: 온라인");
    System.out.println("2: 오프라인");
    study.onOffLine = Prompt.inputString("> ");

    if (study.onOffLine.equals("2")) {
      study.area = Prompt.inputString("지역? ");
    }

    study.title = Prompt.inputString("제목? ");
    study.explanation = Prompt.inputString("설명? ");
    study.rule = Prompt.inputString("룰? ");
    study.registeredDate = new Date(System.currentTimeMillis());

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
      System.out.printf("%d, %s, %s, %s, %d\n", this.studies[i].no, this.studies[i].title, this.studies[i].writer,
          this.studies[i].registeredDate, this.studies[i].viewCount);
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

    System.out.printf("제목: %s\n", study.title);
    System.out.printf("내용: %s\n", study.explanation);
    System.out.printf("팀장: %s\n", study.writer);
    System.out.printf("지역: %s\n", study.area);
    System.out.printf("설명: %s\n", study.explanation);
    System.out.printf("룰: %s\n", study.rule);
    System.out.printf("등록일: %s\n", study.registeredDate);
    System.out.printf("조회수: %d\n", ++study.viewCount);
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

    String title = Prompt.inputString(String.format("[%s] 수정된 제목: ", study.title));
    String explanation = Prompt.inputString(String.format("[%s] 수정된 내용: ", study.explanation));

    String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 수정을 취소하였습니다.");
      return;
    }

    study.title = title;
    study.explanation = explanation;
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

  private String onOffLineLabel(int onOffLine) {
    switch (onOffLine) {
      case 1: return "온라인";
      case 2: return "오프라인";
      default: return null;
    }
  }

  //  private int promptOnOffLine() {
  //    return promptOnOffLine(-1);
  //  }
  //
  //  private int promptOnOffLine(int onOffLine) {
  //    if (onOffLine == -1) {
  //      System.out.println("온/오프라인?");
  //    } else {
  //      System.out.printf("[%s] 온/오프라인?\n", onOffLineLabel(onOffLine));
  //    }
  //    System.out.println("1: 온라인");
  //    System.out.println("2: 오프라인");
  //    return Prompt.inputInt("> ");
  //  }

  // 무료 스터디 번호 조회
  private FreeStudy findByNo(int no) {
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