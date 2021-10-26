package com.studywithus.handler.freestudy;

import java.util.List;
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;
import com.studywithus.util.StudyStatusHelper;

public class RegisterFreeStudyDetailHandler implements Command {

  StudyMemberDao studyMemberDao;

  public RegisterFreeStudyDetailHandler(StudyMemberDao studyMemberDao) {
    this.studyMemberDao = studyMemberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 무료 스터디/ 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Study freeStudy = studyMemberDao.findByNoStudy(AuthLogInHandler.getLoginUser().getNo( ), no, Study.OWNER_STATUS);

    if (freeStudy == null || freeStudy.getPrice() > 0) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    String status = StudyStatusHelper.studyStatus(freeStudy);

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("번호: %d\n",freeStudy.getNo());
    System.out.printf("제목: %s\n", freeStudy.getTitle());
    System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());
    System.out.printf("스터디 진행상태 = %s\n", status);

    if (freeStudy.getOnOffLine() == 1) {
      System.out.printf("온/오프라인: 오프라인");
      System.out.printf("지역: %s\n", freeStudy.getArea());

    } else {
      System.out.printf("온/오프라인: 온라인");
    }
    System.out.printf("시작일: %s\n", freeStudy.getStartDate());
    System.out.printf("종료일: %s\n", freeStudy.getEndDate());
    System.out.printf("모집인원: %d / %d\n", freeStudy.getMembers(), freeStudy.getMaxMembers());
    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());
    System.out.printf("좋아요: %d\n", freeStudy.getLikes());
    System.out.println();

    request.setAttribute("freeNo", no);

    List<Member> applicants = studyMemberDao.findAllMember(no, Study.APPLICANT_STATUS);

    // 무료 스터디 신청 회원 X
    if (applicants.isEmpty()) {
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
          System.out.println("무효한 메뉴 번호입니다.\n");
          continue;
        }
      }

      // 무료 스터디 신청 회원 O
    } else {
      // 내가 생성한 무료 스터디 상세보기 안에서 신청자 명단 출력1
      for (Member freeApplicant : applicants) {
        System.out.printf("[번호 = %d, 이름 = %s]", freeApplicant.getNo(), freeApplicant.getName());
      }

      System.out.println();

      while (true) {
        int applicantNo = Prompt.inputInt("신청자 번호를 입력하세요. > ");
        // Member freeApplicant;

        for (Member applicant : applicants) {
          if (applicantNo == applicant.getNo()) {
            System.out.printf("이름: %s\n", applicant.getName());
            System.out.printf("이메일: %s\n", applicant.getEmail());
            System.out.printf("휴대폰 번호: %s\n", applicant.getPhoneNumber());

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

