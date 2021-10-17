package com.studywithus.handler.freestudy;

import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class RegisterFreeStudyDetailHandlerGR implements Command {

  FreeStudyDao freeStudyDao;

  public RegisterFreeStudyDetailHandlerGR(FreeStudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 무료 스터디/ 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Study freeStudy = freeStudyDao.findByNo(no);

    if (freeStudy.getWriter().getNo() != AuthLogInHandler.getLoginUser().getNo()) {
      System.out.println("번호에 해당하는 내가 생성한 무료 스터디가 없습니다.");
      return;
    }

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("제목: %s\n", freeStudy.getTitle());
    System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

    if (freeStudy.getArea() != null) {
      System.out.printf("온/오프라인: %s\n", freeStudy.getOFFLINE());
      System.out.printf("지역: %s\n", freeStudy.getArea());
    } else {
      System.out.printf("온/오프라인: %s\n", freeStudy.getONLINE());
    }

    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("모집인원: %d / %d\n", freeStudy.getMembers().size(), freeStudy.getMaxMembers());
    System.out.printf("시작일 : %s\n", freeStudy.getStartDate());
    System.out.printf("종료일 : %s\n", freeStudy.getEndDate());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());
    System.out.printf("좋아요: %d\n", freeStudy.getLikeMembers().size());
    System.out.println();

    request.setAttribute("freeNo", no);

    // 무료 스터디 신청 회원 X
    if (freeStudy.getApplicants().isEmpty()) {
      System.out.println("스터디를 신청한 회원이 없습니다.\n");
      System.out.println("1. 수정");
      System.out.println("2. 삭제");
      System.out.println("0. 이전\n");

      while (true) {
        int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
        System.out.println();

        if (input == 1) {
          request.getRequestDispatcher("/freeStudy/update").forward(request);
          return;

        } else if (input == 2) {
          request.getRequestDispatcher("/freeStudy/delete").forward(request);
          return;

        } else if (input == 0) {
          return;

        } else {
          System.out.println("무효한 메뉴 번호입니다.\n");
          continue;
        }
      }

      // 무료 스터디 신청 회원 O
    } else {
      while (true) {
        int applicantNo = Prompt.inputInt("신청자 번호를 입력하세요. > ");

        for (int i = 0; i < freeStudy.getApplicants().size(); i++) {
          if (applicantNo == freeStudy.getApplicants().get(i).getNo()) {
            System.out.printf("이름: %s\n", freeStudy.getApplicants().get(i).getName());
            System.out.printf("이메일: %s\n", freeStudy.getApplicants().get(i).getEmail());
            System.out.printf("휴대폰 번호: %s\n", freeStudy.getApplicants().get(i).getPhoneNumber());

            System.out.println();
            System.out.println("1. 수정");
            System.out.println("2. 삭제");
            System.out.println("3. 승인");
            System.out.println("4. 거절");
            System.out.println("0. 이전\n");

            while (true) {
              int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
              System.out.println();

              if (input == 1) {
                request.getRequestDispatcher("/freeStudy/update").forward(request);
                return;

              } else if (input == 2) {
                request.getRequestDispatcher("/freeStudy/delete").forward(request);
                return;

              } else if (input == 3) {
                request.getRequestDispatcher("/freeStudy/memberApprove").forward(request);
                return;

              } else if (input == 4) {
                request.getRequestDispatcher("/freeStudy/memberRefusal").forward(request);
                return;

              } else if (input == 0) {
                return;

              } else {
                System.out.println("다시 입력하세요.\n");
                continue;
              }
            }

          } else {
            System.out.println();
            System.out.println("입력하신 이름과 일치하는 신청자가 없습니다.");
            continue;
          }
        }
      }
    }
  }
}
