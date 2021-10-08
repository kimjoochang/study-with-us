package com.studywithus.handler.chargestudy;

import java.sql.Date;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ChargeStudyDetailMenuPrompt {

  CommandRequest request;
  RequestAgent requestAgent;
  Study chargeStudy;

  int interestType = 0; // 메서드를 호출할 때, 관심 목록 존재 여부 구분을 위한 변수
  int paymentType = 0; // 메서드를 호출할 때, 관심 목록 존재 여부 구분을 위한 변수


  public ChargeStudyDetailMenuPrompt(RequestAgent requestAgent, CommandRequest request) {
    this.request = request;
    this.requestAgent = requestAgent;
  }

  protected String studyStatus(Study chargeStudy) {
    this.chargeStudy = chargeStudy;
    // 현재 날짜 < 시작일인 경우
    if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getStartDate()) == -1) {
      chargeStudy.setStudyStatus("모집중");

      // 현재 날짜 < 종료일
    } else if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getEndDate()) == -1) {
      chargeStudy.setStudyStatus("진행중");

    } else {
      chargeStudy.setStudyStatus("진행종료");
    }
    return chargeStudy.getStudyStatus();
  }

  protected void myStudySelectedMenu() throws Exception {
    int input = myStudyMenu();

    if (input == 1) {
      request.getRequestDispatcher("/chargeStudy/update").forward(request);

    } else if (input == 2) {

      if (chargeStudy.isDeleteRequest()) {
        request.getRequestDispatcher("/chargeStudy/deleteRequestCancel").forward(request);

      } else {
        request.getRequestDispatcher("/chargeStudy/deleteRequest").forward(request);
      }

    } else if (input == 3) {
      if (chargeStudy.getStudyStatus().equals("진행종료")) {
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

  protected void anotherStudySelectedMenu() throws Exception {
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
      if (interestType == 0) {
        request.getRequestDispatcher("/chargeStudy/interestAdd").forward(request);

        // 관심목록 삭제 (관심 목록에 추가하지 않은 경우에 보이는 2번 메뉴)
      } else {
        request.getRequestDispatcher("/chargeStudy/interestDelete").forward(request);
      }

    } else if (input == 3) { 

      if (chargeStudy.getStudyStatus().equals("진행종료")) {
        request.getRequestDispatcher("/review/list").forward(request);

      } else {
        System.out.println("존재하지 않는 메뉴 번호 입니다.");
      }

    } else if (input == 4) {
      if (findByName()) {
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
    interestType= 0;

    for (String member : chargeStudy.getMenteeEmailList()) {
      if (member.equals(AuthLogInHandler.getLoginUser().getEmail())) {
        paymentType = 1;
        break;
      }
    }

    for (String email : chargeStudy.getLikeMembersEmail()) {
      if (email.equals(AuthLogInHandler.getLoginUser().getEmail())) {
        interestType = 1;
        break;
      }
    }

    if (paymentType == 0) {
      System.out.println("1. 결제");
    } else {
      System.out.println("1. 결제 취소하기");
    }

    // 관심 목록이 없는 경우
    if (interestType == 0) {
      System.out.println("2. 관심목록 추가");

      // 관심 목록이 있는 경우
    } else {
      System.out.println("2. 관심목록 삭제");
    }

    if (chargeStudy.getStudyStatus().equals("진행종료")) {
      System.out.println("3. 후기 보기");

      if (findByName()) {
        System.out.println("4. 후기 작성");
      }
    }
    System.out.println("0. 이전\n");
    System.out.println();

    return Prompt.inputInt("메뉴 번호를 선택하세요. > ");
  }

  private int myStudyMenu() {
    String selectedMenu;

    if (chargeStudy.isDeleteRequest() == true) {
      selectedMenu = "삭제요청 취소";
    } else {
      selectedMenu = "삭제요청";
    }

    System.out.println("1. 수정");
    System.out.println("2. "+ selectedMenu);
    if (chargeStudy.getStudyStatus().equals("진행종료")) {
      System.out.println("3. 후기 보기");
    }
    System.out.println("0. 이전\n");

    return Prompt.inputInt("메뉴 번호를 선택하세요. > "); 
  }

  private boolean findByName() throws Exception {
    requestAgent.request("chargeStudy.selectList", null);

    for (Study chargeStudy : requestAgent.getObjects(Study.class)) {

      for (String email : chargeStudy.getMenteeEmailList()) {
        if (email.equals(AuthLogInHandler.getLoginUser().getEmail())) {
          return true;
        }
      }
    }
    return false;
  }
}
