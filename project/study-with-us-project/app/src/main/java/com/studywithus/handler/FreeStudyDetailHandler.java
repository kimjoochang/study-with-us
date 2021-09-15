package com.studywithus.handler;

import java.util.ArrayList;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class FreeStudyDetailHandler extends AbstractStudyHandler {

  // 무료 스터디 신청 리스트 (회원 관점)
  List<Study> freeApplicationList;

  // 무료 스터디 관심목록 리스트 (회원 관점)
  List<Study> freeInterestList;

  public FreeStudyDetailHandler(List<Study> freeStudyList, List<Study> freeApplicationList, List<Study> freeInterestList) {
    super(freeStudyList);
    this.freeApplicationList = freeApplicationList;
    this.freeInterestList = freeInterestList;
  }

  @Override
  public void execute() {
    System.out.println("[무료 스터디 / 상세보기]\n");

    int no = Prompt.inputInt("번호? ");
    Study freeStudy = findByNo(no);

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

    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());

    System.out.println();

    System.out.println("1. 신청");

    //  해당 스터디의 관심목록 존재 유/무에 따라 관심목록 삭제/추가로 나뉨
    for (Study freeInterest : freeInterestList) {
      if (freeInterest.equals(freeInterest)) {
        System.out.println("2. 관심목록 삭제");
        break;
      }

      else {
        System.out.println("2. 관심목록 추가");
        break;
      }
    }

    System.out.println("0. 이전\n");

    while (true) {
      int input = Prompt.inputInt("선택> ");

      if (input == 1) {
        apply(freeStudy);

      } else if (input == 2) {
        if (no == 0) {
          interestDelete(freeStudy);
          return;
        }
        interest(freeStudy);

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      return;
    }
  }

  // 무료 스터디 신청
  public void apply(Study freeStudy) {
    System.out.println("[무료 스터디 / 상세보기 / 신청]\n");

    String input = Prompt.inputString("무료 스터디를 신청 하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 신청이 취소되었습니다.");
      return;
    }

    // 무료 스터디 신청자 리스트 (팀장 관점)
    List<Member> freeApplicantList = new ArrayList<>();

    // 무료 스터디 신청자 리스트에 회원 정보 추가 (멘토 관점)
    freeApplicantList.add(AuthLoginHandler.getLoginUser());

    // 무료 스터디 정보에 스터디 신청자 리스트 추가 (팀장 관점)
    freeStudy.setApplicants(freeApplicantList);

    // 무료 스터디 신청 리스트에 신청한 무료 스터디 추가 (회원 관점)
    freeApplicationList.add(freeStudy);

    System.out.println();
    System.out.println("무료 스터디 신청이 완료되었습니다.\n");
  }

  // 무료 스터디 관심목록 추가
  public void interest(Study freeStudy) {
    System.out.println("[무료 스터디 / 상세보기 / 관심 목록]\n");

    String input = Prompt.inputString("무료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }

    // 무료 스터디 관심 목록에 좋아요한 무료 스터디 추가 (회원 관점)
    freeInterestList.add(freeStudy);
    freeStudy.setViewCount(freeStudy.getLike() + 1);

    System.out.println();
    System.out.println("무료 스터디 관심 목록에 추가되었습니다.\n");
  }

  // 무료 스터디 관심목록 삭제
  private void interestDelete(Study freeStudy) {
    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    freeInterestList.remove(freeStudy);
    freeStudy.setViewCount(freeStudy.getLike() - 1);

    System.out.println();
    System.out.println("무료 스터디 관심 목록을 삭제하였습니다.\n");
  }
}
