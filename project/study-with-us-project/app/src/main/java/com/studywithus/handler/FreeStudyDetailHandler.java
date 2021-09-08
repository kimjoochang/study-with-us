package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ApplicantInfo;
import com.studywithus.domain.FreeStudy;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class FreeStudyDetailHandler extends AbstractFreeStudyHandler {

  FreeStudy freeStudy;

  public FreeStudyDetailHandler(List<FreeStudy> freeStudyList, List<FreeStudy> freeStudyApplyList, List<FreeStudy> freeInterestList) {
    super(freeStudyList, freeStudyApplyList, freeInterestList);
  }

  // 무료 스터디 상세보기
  public void execute() {
    System.out.println("[무료 스터디 / 상세보기]\n");

    int no = Prompt.inputInt("번호? ");
    freeStudy = findByNo(no);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", freeStudy.getTitle());
    System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

    if (freeStudy.getArea() != null) {
      System.out.printf("지역: %s\n", freeStudy.getArea());
    }

    System.out.printf("설명: %s\n", freeStudy.getExplanation());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());

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

    ApplicantInfo.getName();
    ApplicantInfo.getId();

    freeStudyApplyList.add(freeStudy);

    Menu.ACCESS_GENERAL = Menu.ACCESS_LEADER;

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

    freeInterestList.add(freeStudy);

    System.out.println();
    System.out.println("무료 스터디 관심 목록에 추가되었습니다.\n");
  }
}
