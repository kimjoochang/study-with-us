package com.studywithus.handler.chargestudy;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyDetailHandler extends AbstractStudyHandler {

  Study chargeStudy;
  List<Study> chargeInterestList; // 유료 스터디 관심목록 리스트 (회원 관점)

  public ChargeStudyDetailHandler(List<Study> chargeStudyList, List<Study> chargeInterestList) {
    super(chargeStudyList);
    this.chargeInterestList = chargeInterestList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기]\n");

    int no = Prompt.inputInt("번호? ");
    chargeStudy = findByNo(no);

    if (chargeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
      return;
    }

    chargeStudy.setViewCount(chargeStudy.getViewCount() + 1);

    System.out.printf("제목: %s\n", chargeStudy.getTitle());
    System.out.printf("설명: %s\n", chargeStudy.getContent());
    System.out.printf("지역: %s\n", chargeStudy.getArea());
    System.out.printf("멘토: %s\n", chargeStudy.getWriter().getName());
    System.out.printf("가격: %s\n", chargeStudy.getPrice());
    System.out.printf("등록일: %s\n", chargeStudy.getRegisteredDate());
    System.out.printf("조회수: %d\n", chargeStudy.getViewCount());
    System.out.println();

    Member loginUser = AuthLogInHandler.getLoginUser(); 
    if (loginUser == null || chargeStudy.getWriter() != loginUser) {
      return;
    }

    request.setAttribute("no", no);

    // 본인이 작성한 글인 경우
    if (chargeStudy.getWriter() == loginUser) {
      System.out.println("1. 수정");
      System.out.println("2. 삭제");

      // 타인이 작성한 글인 경우
    } else {
      System.out.println("1. 결제");

      //  관심목록 존재 여부에 따라 메뉴 출력
      for (Study chargeInterest : chargeInterestList) {

        // 관심 목록이 있는 경우
        if (chargeStudy.equals(chargeInterest)) {
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
        request.getRequestDispatcher("/chargeStudy/update").forward(request);
        return;

      } else if (input == 2) {
        request.getRequestDispatcher("/chargeStudy/deleteRequest").forward(request);
        return;

      } else if (input == 3) {
        request.getRequestDispatcher("/chargeStudy/payment").forward(request);
        return;

      } else if (input == 4) {
        if (no == 0) {
          request.getRequestDispatcher("/chargeStudy/interestDelete").forward(request);
          return;
        }
        request.getRequestDispatcher("/chargeStudy/interestAdd").forward(request);

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
