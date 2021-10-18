package com.studywithus.handler.freestudy;

import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class FreeStudyMemberRefusalHandler implements Command {

  FreeStudyDao freeStudyDao;

  public FreeStudyMemberRefusalHandler(FreeStudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 무료 스터디/ 상세보기 / 승인 거절]\n");
    int no = (int) request.getAttribute("freeNo");
    Study freeStudy = freeStudyDao.findByNo(no);

    if (freeStudy == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    while (true) {
      for (Member freeApplicant : freeStudy.getApplicants()) {
        System.out.printf("[번호 = %d, 이름 = %s]", freeApplicant.getNo(), freeApplicant.getName());
      }
      int applicantNo = Prompt.inputInt("신청자 번호를 입력하세요. > ");
      String input = Prompt.inputString("해당 회원을 승인 거절하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println("멤버 승인 거절이 취소되었습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        for (int i = 0; i < freeStudy.getApplicants().size(); i++) {
          if (applicantNo == freeStudy.getApplicants().get(i).getNo())
            freeStudy.getApplicants().remove(i);
        }

        freeStudyDao.update(freeStudy);

        System.out.println();
        System.out.println("멤버 승인 거절이 완료되었습니다.");
        return;

      } else {
        System.out.println();
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
    // System.out.println("무료스터디 멤버 승인 거절이 완료되었습니다.");
  }
}
