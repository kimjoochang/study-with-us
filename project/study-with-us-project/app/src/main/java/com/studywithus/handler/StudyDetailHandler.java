package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class StudyDetailHandler extends AbstractStudyHandler {

  Study Study;

  public StudyDetailHandler(List<Study> StudyList, List<Study> StudyApplyList, List<Study> InterestList) {
    super(StudyList, StudyApplyList, InterestList);
  }

  // 무료 스터디 상세보기
  public void execute() {
    System.out.println("[무료 스터디 / 상세보기]\n");

    int no = Prompt.inputInt("번호? ");
    Study = findByNo(no);

    if (Study == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", Study.getTitle());
    System.out.printf("팀장: %s\n", Study.getWriter().getName());

    if (Study.getArea() != null) {
      System.out.printf("지역: %s\n", Study.getArea());
    }

    System.out.printf("설명: %s\n", Study.getExplanation());
    System.out.printf("규칙: %s\n", Study.getRule());
    System.out.printf("등록일: %s\n", Study.getRegisteredDate());

    Study.setViewCount(Study.getViewCount() + 1);
    System.out.printf("조회수: %d\n", Study.getViewCount());

    System.out.println();

    System.out.println("1. 신청");
    System.out.println("2. 관심목록 추가");
    System.out.println("0. 이전 메뉴");

    int input = Prompt.inputInt("선택 > ");

    switch (input) {
      case 1: apply(); break;
      case 2: interest(); break;
      default: return;
    }
  }

  // 무료 스터디 신청
  public void apply() {
    System.out.println("[무료 스터디 / 상세보기 / 신청]\n");

    String input = Prompt.inputString("무료 스터디를 신청 하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 신청이 취소되었습니다.");
      return;
    }

    Member.setName(AuthLoginHandler.getLoginUser());
    ApplicantInfo.getId();

    StudyApplyList.add(Study);

    System.out.println();
    System.out.println("무료 스터디 신청이 완료되었습니다.\n");
  }

  // 무료 스터디 관심목록 추가
  public void interest() {
    System.out.println("[무료 스터디 / 상세보기 / 관심 목록]\n");

    String input = Prompt.inputString("무료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }

    InterestList.add(Study);

    System.out.println();
    System.out.println("무료 스터디 관심 목록에 추가되었습니다.\n");
  }
}
