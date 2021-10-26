package com.studywithus.handler.chargestudy;

import com.studywithus.dao.StudyDao;
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyDetailMenuPrompt {

  CommandRequest request;
  StudyDao chargeStudyDao;
  StudyMemberDao studyMemberDao;
  Study chargeStudy;

  int count;
  Study findWriter;
  int paymentType = 0; // 메서드를 호출할 때, 관심 목록 존재 여부 구분을 위한 변수

  int no;

  public ChargeStudyDetailMenuPrompt(StudyDao chargeStudyDao, StudyMemberDao studyMemberDao, CommandRequest request) {
    this.request = request;
    this.chargeStudyDao = chargeStudyDao;
    this.studyMemberDao = studyMemberDao;
  }

  protected void myStudySelectedMenu(Study study) throws Exception {
    chargeStudy = study;
    int input = myStudyMenu();

    if (input == 1) {
      request.getRequestDispatcher("/chargeStudy/update").forward(request);

    } else if (input == 2) {

      if (chargeStudy.getDeleteStatus() == 1 && chargeStudy.getPrice() > 0) {
        request.getRequestDispatcher("/chargeStudy/deleteRequestCancel").forward(request);

      } else {
        request.getRequestDispatcher("/chargeStudy/deleteRequest").forward(request);
      }

    } else if (input == 3) {
      if (chargeStudy.getStudyStatus() == 2) {
        request.getRequestDispatcher("/review/list").forward(request);

      } else {
        System.out.println("존재하지 않는 메뉴 번호 입니다.");
      }

    } else if (input == 0) {
      return;

    }  else {
      System.out.println("존재하지 않는 메뉴 번호 입니다.");
      return;
    }
  }

  protected void anotherStudySelectedMenu(Study study) throws Exception {
    chargeStudy = study;
    int input = anotherStudyMenu();

    // 1. 결제 or 결제 취소
    if (input == 1) {

      // 결제를 아직 안 한 경우
      if (paymentType == 0) {
        request.getRequestDispatcher("/chargeStudy/payment").forward(request);
        // 결제 한 경우
      } else {
        request.getRequestDispatcher("/chargeStudy/paymentCancel").forward(request);
      }

      // 2. 관심목록 추가 or 삭제
    } else if (input == 2) {

      // 관심목록 추가 (관심 목록에 추가하지 않은 경우에 보이는 2번 메뉴)
      if (count == 0) {
        request.getRequestDispatcher("/chargeStudy/interestAdd").forward(request);

        // 관심목록 삭제 (관심 목록에 추가하지 않은 경우에 보이는 2번 메뉴)
      } else {
        request.getRequestDispatcher("/chargeStudy/interestDelete").forward(request);
      }

    } else if (input == 3) { 

      if (chargeStudy.getStudyStatus() == 2) {
        request.getRequestDispatcher("/review/list").forward(request);

      } else {
        System.out.println("존재하지 않는 메뉴 번호 입니다.");
      }

    } else if (input == 4) {
      if (findWriter == null) {
        request.getRequestDispatcher("/review/add").forward(request);

      } else {
        System.out.println("존재하지 않는 메뉴 번호 입니다.");
      }

    } else if (input == 0) {
      return;

    } else {
      System.out.println("존재하지 않는 메뉴 번호입니다.");
    }
  }

  private int anotherStudyMenu() throws Exception {
    paymentType = 0;

    count = chargeStudyDao.findMyInterest(AuthLogInHandler.getLoginUser().getNo( ), chargeStudy.getNo());

    if (paymentType == 0) {
      System.out.println("1. 결제");
    } else {
      System.out.println("1. 결제 취소하기");
    }

    // 관심 목록이 없는 경우
    if (count == 0) {
      System.out.println("2. 관심목록 추가");

      // 관심 목록이 있는 경우
    } else {
      System.out.println("2. 관심목록 삭제");
    }

    if (chargeStudy.getStudyStatus() == 2) {
      System.out.println("3. 후기 보기");

      findWriter = studyMemberDao.findByNoStudy(AuthLogInHandler.getLoginUser().getNo(), no, Study.OWNER_STATUS);  
      if (findWriter == null) {
        System.out.println("4. 후기 작성");
      }
    }
    System.out.println("0. 이전\n");
    System.out.println();

    return Prompt.inputInt("메뉴 번호를 선택하세요. > ");
  }

  private int myStudyMenu() {
    String selectedMenu;

    if (chargeStudy.getDeleteStatus() == 1 && chargeStudy.getPrice() > 0) {
      selectedMenu = "삭제요청 취소";
    } else {
      selectedMenu = "삭제요청";
    }

    System.out.println("1. 수정");
    System.out.println("2. "+ selectedMenu);
    if (chargeStudy.getStudyStatus() == 2) {
      System.out.println("3. 후기 보기");
    }
    System.out.println("0. 이전\n");

    return Prompt.inputInt("메뉴 번호를 선택하세요. > "); 
  }

}
