package com.studywithus.handler.study;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyDetailHandler extends AbstractStudyHandler {

  List<Member> chargeApplicantList;

  public ChargeStudyDetailHandler(List<Study> chargeStudyList, List<Member> chargeApplicantList) {
    super(chargeStudyList);
    this.chargeApplicantList = chargeApplicantList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Study chargeStudy = findByNo(no);

    if (chargeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
      return;
    }

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

    System.out.printf("모집인원: %d / %d\n", chargeStudy.getMembers().size(), chargeStudy.getMaxMembers());
    System.out.printf("조회수: %d\n", chargeStudy.getViewCount());
    System.out.printf("좋아요수: %d\n", chargeStudy.getLikeMembers().size());
    System.out.println();

    // ChargeStudyUpdateHandler나 ChargeStudyDeleteHandler를 실행할 때 
    // 게시글 번호를 사용할 수 있도록 CommandRequest에 보관한다.
    request.setAttribute("chargeNo", no);

    // 본인이 작성한 글 상세보기 시 경우 보이는 메뉴
    if (chargeStudy.getWriter().getId().equals(AuthLogInHandler.getLoginUser().getId())) {
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
          request.getRequestDispatcher("/chargeStudy/deleteRequest").forward(request);

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
      int paymentType = 0; // 메서드를 호출할 때, 관심 목록 존재 여부 구분을 위한 변수

      while (true) {
        for (Member member : chargeStudy.getMembers()) {
          if (member.getId().equals(AuthLogInHandler.getLoginUser().getId())) {
            paymentType = 1;
            break;
          }
        }

        for (Member member : chargeStudy.getLikeMembers()) {
          if (member.getId().equals(AuthLogInHandler.getLoginUser().getId())) {
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

        int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
        System.out.println();

        // 1. 결제 or 결제 취소
        if (input == 1) {

          // 1. 결제를 아직 안 한 경우
          if (paymentType == 0) {
            request.getRequestDispatcher("/chargeStudy/payment").forward(request);

          } else {
            request.getRequestDispatcher("/chargeStudy/paymentCancel").forward(request);
          }

          // 2. 관심목록 추가 or 삭제
          // 현재 스터디가 회원의 관심목록에 존재하는지 아닌지에 따라 2번 메뉴 출력을 다르게 함
        } else if (input == 2) {

          // 2. 관심목록 추가 (관심 목록에 추가하지 않은 경우에 보이는 2번 메뉴)
          if (interestType == 0) {
            request.getRequestDispatcher("/chargeStudy/interestAdd").forward(request);

            // 2. 관심목록 삭제 (관심 목록에 추가하지 않은 경우에 보이는 2번 메뉴)
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

        } else if (input == 4) {
          if (findByName()) {
            request.getRequestDispatcher("/review/add").forward(request);

          } else {
            System.out.println("존재하지 않는 메뉴 번호 입니다.");
            continue;
          }

        }else if (input == 0) {
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

  private boolean findByName() {
    for (Study study : studyList) {
      for (Member member : study.getMembers()) {
        if (member.getId().equals(AuthLogInHandler.getLoginUser().getId())) {
          return true;
        }
      }
    }
    return false;
  }

}

