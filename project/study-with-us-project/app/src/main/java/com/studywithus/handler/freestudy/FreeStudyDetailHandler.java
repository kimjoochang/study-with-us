package com.studywithus.handler.freestudy;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.abstract.AbstractStudyHandler;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyDetailHandler extends AbstractStudyHandler {

  List<Study> freeInterestList; // 무료 스터디 관심목록 리스트 (회원 관점)

  public FreeStudyDetailHandler(List<Study> freeStudyList, List<Study> freeInterestList) {
    super(freeStudyList);
    this.freeInterestList = freeInterestList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 상세보기]\n");

    int type = 0; // 관심목록 추가 여부에 따라 실행 메서드 구분하기 위한 변수

    int no = Prompt.inputInt("번호? ");
    Study freeStudy = findByNo(no);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);

    System.out.printf("제목: %s\n", freeStudy.getTitle());
    System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

    if (freeStudy.getArea() != null) {
      System.out.printf("지역: %s\n", freeStudy.getArea());
    }

    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());
    System.out.println();

    request.setAttribute("no", no);

    // 본인이 작성한 글인 경우
    if (freeStudy.getWriter() == AuthLogInHandler.getLoginUser()) {
      System.out.println("1. 수정");
      System.out.println("2. 삭제");

      // 타인이 작성한 글인 경우
    } else {
      System.out.println("1. 신청");

      //  관심목록 존재 여부에 따라 메뉴 출력
      for (Study freeInterest : freeInterestList) {

        // 관심 목록이 있는 경우
        if (freeStudy.equals(freeInterest)) {
          System.out.println("2. 관심목록 삭제");
          break;
        }

        // 관심 목록이 없는 경우
        else {
          System.out.println("2. 관심목록 추가");
          break;
        }
      }
    }

    System.out.println("0. 이전\n");

    while (true) {
      int input = Prompt.inputInt("선택> ");

      if (input == 1) {
        request.getRequestDispatcher("/freeStudy/application").forward(request);

      } else if (input == 2) {
        if (type == 0) {
          request.getRequestDispatcher("/freeStudy/interestDelete").forward(request);
          return;

        } else if (type == 1) {
          request.getRequestDispatcher("/freeStudy/interestAdd").forward(request);
        }

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      return;
    }
  }
}
