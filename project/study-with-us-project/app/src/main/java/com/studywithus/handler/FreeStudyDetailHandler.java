package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ApplicantInfo;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyDetailHandler extends AbstractFreeStudyHandler {

  public FreeStudyDetailHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);
  }

  // 무료 스터디 상세보기
  public void execute() {
    System.out.println("[무료 스터디 / 상세보기]\n");

    int no = Prompt.inputInt("번호? ");
    FreeStudy study = findByNo(no);

    if (study == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", study.getTitle());
    System.out.printf("팀장: %s\n", study.getWriter());

    if (study.getArea() != null) {
      System.out.printf("지역: %s\n", study.getArea());
    }

    System.out.printf("설명: %s\n", study.getExplanation());
    System.out.printf("규칙: %s\n", study.getRule());
    System.out.printf("등록일: %s\n", study.getRegisteredDate());

    study.setViewCount(study.getViewCount() + 1);
    System.out.printf("조회수: %d\n", study.getViewCount());

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

  public void apply() {
    System.out.println("[무료 스터디 / 상세보기 / 신청]\n");

    String input = Prompt.inputString("무료 스터디를 신청 하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 신청이 취소되었습니다.");
      return;
    }

    ApplicantInfo.getName();
    ApplicantInfo.getId();

    System.out.println();
    System.out.println("무료 스터디 신청이 완료되었습니다.");
  }

  public void interest() {
    System.out.println("[무료 스터디 / 상세보기 / 관심 목록 추가]\n");

    FreeStudy freeStudy = new FreeStudy();

    String input = Prompt.inputString("무료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }

    freeStudyList.add(freeStudy);

    System.out.println();
    System.out.println("무료 스터디 관심 목록에 추가되었습니다.");
  }
}
