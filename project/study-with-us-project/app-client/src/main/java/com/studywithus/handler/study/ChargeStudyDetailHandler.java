package com.studywithus.handler.study;

import java.sql.Date;
import java.util.HashMap;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ChargeStudyDetailHandler implements Command {

  RequestAgent requestAgent;

  public ChargeStudyDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("chargeStudy.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
      return;
    }

    Study chargeStudy = requestAgent.getObject(Study.class);

    chargeStudy.setViewCount(chargeStudy.getViewCount() + 1);

    System.out.printf("제목: %s\n", chargeStudy.getTitle());
    System.out.printf("멘토: %s\n", chargeStudy.getWriter().getName());

    System.out.printf("설명: %s\n", chargeStudy.getContent());
    System.out.printf("지역: %s\n", chargeStudy.getArea());
    System.out.printf("가격: %s\n", chargeStudy.getPrice());
    System.out.printf("시작일: %s\n", chargeStudy.getStartDate());
    System.out.printf("종료일: %s\n", chargeStudy.getEndDate());
    System.out.printf("스터디 진행상태: %s\n", StudyStatus(chargeStudy));
    System.out.printf("등록일: %s\n", chargeStudy.getRegisteredDate());

    System.out.printf("모집인원 = %d / %d\n", chargeStudy.getMenteeEmailList().size(), chargeStudy.getMaxMembers());
    System.out.printf("조회수: %d\n", chargeStudy.getViewCount());
    System.out.printf("좋아요수: %d\n", chargeStudy.getLikeMembersEmail().size());
    System.out.println();

    // ChargeStudyUpdateHandler나 ChargeStudyDeleteHandler를 실행할 때 
    // 게시글 번호를 사용할 수 있도록 CommandRequest에 보관한다.
    request.setAttribute("chargeNo", no);

    // 본인이 작성한 글 상세보기 시 경우 보이는 메뉴
    if (chargeStudy.getWriter().getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
      while (true) {
        String selectedMenu = selectMenu(chargeStudy);

        System.out.println("1. 수정");
        System.out.println("2. "+ selectedMenu);
        if (chargeStudy.getStudyStatus().equals("진행종료")) {
          System.out.println("3. 후기 보기");
        }
        System.out.println("0. 이전\n");

        int input = Prompt.inputInt("메뉴 번호를 선택하세요. > "); 
        System.out.println();

        if (input == 1) {
          request.getRequestDispatcher("/chargeStudy/update").forward(request);

        } else if (input == 2) {

          if (chargeStudy.isDeleteRequest()) {
            request.getRequestDispatcher("/chargeStudy/deleteRequestCancel").forward(request);

          } else {
            request.getRequestDispatcher("/chargeStudy/deleteRequest").forward(request);
          }

        } else if (input == 0) {
          return;

        } else if (input == 3) {
          if (chargeStudy.getStudyStatus().equals("진행종료")) {
            request.getRequestDispatcher("/review/list").forward(request);

          } else {
            System.out.println("존재하지 않는 메뉴 번호 입니다.");
            continue;
          }

        } else {
          System.out.println("존재하지 않는 메뉴 번호 입니다.");
          continue;
        }
        return;
      }

      // 타인이 작성한 글 상세보기 시 보이는 메뉴
    } else {

      int interestType = 0; // 메서드를 호출할 때, 관심 목록 존재 여부 구분을 위한 변수
      int paymentType = 0; // 메서드를 호출할 때, 결제 내역 존재 여부 구분을 위한 변수

      while (true) {
        for (String member : chargeStudy.getMenteeEmailList()) {
          if (member.equals(AuthLogInHandler.getLoginUser().getEmail())) {
            paymentType = 1;
            break;
          }
        }

        // 아직 결제하지 않은 회원이라면 출력될 1번 메뉴 옵션
        if (paymentType == 0) {
          System.out.println("1. 결제");

          // 이미 결제한 회원이라면 출력될 1번 메뉴 옵션
        } else {
          System.out.println("1. 결제 취소하기");
        }

        for (String email : chargeStudy.getLikeMembersEmail()) {
          if (email.equals(AuthLogInHandler.getLoginUser().getEmail())) {
            interestType = 1;
            break;
          }
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

          /*if (findByName()) {
            System.out.println("4. 후기 작성");
          }*/
        }
        System.out.println("0. 이전\n");
        System.out.println();

        int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
        System.out.println();

        /* 1. 결제 or 결제 취소
        -> 현재 상세보기 중인 스터디가 회원의 결제내역에 존재하는지 아닌지에 따라
           1번 메뉴 출력을 다르게 함
         */
        if (input == 1) {

          // 결제를 아직 안 한 경우, 결제하기로 이동함
          if (paymentType == 0) {
            request.getRequestDispatcher("/chargeStudy/payment").forward(request);

            // 결제한 경우 결제 취소하기로 이동함
          } else {
            request.getRequestDispatcher("/chargeStudy/paymentCancel").forward(request);
          }

          /* 2. 관심목록 추가 or 삭제
             -> 현재 상세보기 중인 스터디가 회원의 관심목록에 존재하는지 아닌지에 따라
                2번 메뉴 출력을 다르게 함
           */  
        } else if (input == 2) {

          // 관심목록에 추가되어있지 않은 경우, 추가하기로 이동함
          if (interestType == 0) {
            request.getRequestDispatcher("/chargeStudy/interestAdd").forward(request);

            // 관심목록에 추가되어 있는 경우, 관심목록 삭제로 이동함
          } else {
            request.getRequestDispatcher("/chargeStudy/interestDelete").forward(request);
          }

        } else if (input == 3) { 

          if (chargeStudy.getStudyStatus().equals("진행종료")) {
            request.getRequestDispatcher("/review/list").forward(request);

          } else {
            System.out.println("존재하지 않는 메뉴 번호 입니다.");
            continue;
          }

        } /* else if (input == 4) {
          if (findByName()) {
            request.getRequestDispatcher("/review/add").forward(request);

          } else {
            System.out.println("존재하지 않는 메뉴 번호 입니다.");
            continue;
          }

        } */

        else if (input == 0) {
          return;

        } else {
          System.out.println("존재하지 않는 메뉴 번호입니다.");
          continue;
        }
        return;
      }
    }
  }

  private String selectMenu(Study chargeStudy) {
    if (chargeStudy.isDeleteRequest() == true) {
      return "삭제요청 취소";
    }
    return "삭제요청";
  }

  private String StudyStatus(Study chargeStudy) {
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

  /* private boolean findByName() {
    for (Study study : studyList) {
      for (Member member : study.getMembers()) {
        if (member.getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
          return true;
        }
      }
    }
    return false;
  }*/
}

