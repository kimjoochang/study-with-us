package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyDetailHandler extends AbstractStudyHandler {

  public FreeStudyDetailHandler(List<Study> freeStudyList) {
    super(freeStudyList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Study freeStudy = findByNo(no);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", freeStudy.getTitle());
    System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

    if (freeStudy.getArea() != null) {
      System.out.printf("온/오프라인: %s\n", freeStudy.getOFFLINE());
      System.out.printf("지역: %s\n", freeStudy.getArea());

    } else {
      System.out.printf("온/오프라인: %s\n", freeStudy.getONLINE());
    }

    System.out.printf("시작일: %s\n", freeStudy.getStartDate());
    System.out.printf("종료일: %s\n", freeStudy.getEndDate());
    System.out.printf("모집인원: %d / %d\n", freeStudy.getMembers().size(), freeStudy.getMaxMembers());
    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());
    System.out.printf("좋아요: %d\n", freeStudy.getLikeMembers().size());
    System.out.println();

    // FreeStudyUpdateHandler나 FreeStudyDeleteHandler를 실행할 때
    // 게시글 번호를 사용할 수 있도록 CommandRequest에 보관한다.
    request.setAttribute("freeNo", no);

    // 내가 쓴 글일 경우
    if (freeStudy.getWriter().getId().equals(AuthLogInHandler.getLoginUser().getId())) {
      while (true) {
        System.out.println("1. 수정");
        System.out.println("2. 삭제");
        System.out.println("0. 이전");
        System.out.println();

        int num = Prompt.inputInt("번호를 입력하세요. > "); // 위에 있는 변수 no와 변수명 겹쳐서 num으로 변경
        System.out.println();

        if (num == 1) {
          request.getRequestDispatcher("/freeStudy/update").forward(request);

        } else if (num == 2) {
          request.getRequestDispatcher("/freeStudy/delete").forward(request);

        } else if (num == 0) {
          return;

        } else {
          System.out.println("무효한 메뉴 번호입니다.\n");
          continue;
        }
        return;
      }

      // 내가 쓴 글이 아닐경우
    } else {
      int interestType = 0; // 메서드 호출할 때, 관심목록 존재 여부 구분을 위한 변수
      int applyType = 0; // 메서드 호출할 때, 관심목록 존재 여부 구분을 위한 변수

      while (true) {
        for (Member member : freeStudy.getApplicants()) {
          if (member.getId().equals(AuthLogInHandler.getLoginUser().getId())) {
            applyType = 1;
            break;
          }
        }

        if (applyType == 0) {
          System.out.println("1. 신청하기");

        } else {
          System.out.println("1. 신청 취소하기");
        }

        for (Member member : freeStudy.getLikeMembers()) {
          if (member.getId().equals(AuthLogInHandler.getLoginUser().getId())) {
            interestType = 1;
            break;
          }
        }

        // 관심목록 존재 여부에 따라 출력문 출력
        if (interestType == 0) {
          System.out.println("2. 관심목록 추가");

        } else {
          System.out.println("2. 관심목록 삭제");
        }
        System.out.println("0. 이전");
        System.out.println();

        int num = Prompt.inputInt("메뉴 번호를 선택하세요. > "); // 위에 있는 변수 no와 변수명 겹쳐서 num으로 변경

        if (num == 1) {
          // 신청하기를 아직 안 한 경우
          if (applyType == 0) {
            request.getRequestDispatcher("/freeStudy/apply").forward(request);

          } else {
            // 신청하기를 이미 한 경우
            request.getRequestDispatcher("/freeStudy/applyCancel").forward(request);
          }

        } else if (num == 2) {
          if (interestType == 0) {
            // 관심목록에 없는 경우
            request.getRequestDispatcher("/freeStudy/addInterest").forward(request);

          } else {
            // 관심목록에 이미 있는 경우
            request.getRequestDispatcher("/freeStudy/deleteInterest").forward(request);
          }

        } else if (num == 0) {
          return;

        } else {
          System.out.println("무효한 메뉴 번호입니다.\n");
          continue;
        }
        return;
      }
    }
  }
}
