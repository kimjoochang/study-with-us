package com.studywithus.handler.freestudy;

import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ParticipateFreeStudyDetailHandler implements Command {

  FreeStudyDao freeStudyDao;

  public ParticipateFreeStudyDetailHandler(FreeStudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 내가 참여한 무료 스터디 / 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Study freeStudy = freeStudyDao.findByNo(no);

    if (freeStudy == null) {
      System.out.println("해당 번호의 무료 스터디가 없습니다.");
      return;
    }

    // int count = 0;

    // 무료 스터디 작성자 == 로그인 회원
    // if (freeStudy.getWriter().getNo() == AuthLogInHandler.getLoginUser().getNo()
    // && no == freeStudy.getNo()) {
    // count++;

    for (Member participant : freeStudy.getParticipants()) {
      if (participant.getNo() != AuthLogInHandler.getLoginUser().getNo()) {
        System.out.println("참여한 무료 스터디가 존재하지 않습니다.");
        return;
      }
    }

    System.out.println();
    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("번호: %d\n", freeStudy.getNo());
    System.out.printf("제목: %s\n", freeStudy.getTitle());
    System.out.printf("내용: %s\n", freeStudy.getContent());
    System.out.printf("작성자: %s\n", freeStudy.getWriter().getName());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());
    System.out.println();
    // }

    // if (count == 0) {
    // System.out.println("내가 작성한 게시글이 존재하지 않습니다.");
    // return;
    // }

    // Member loginUser = AuthLogInHandler.getLoginUser();
    // if (loginUser == null || (freeStudy.getWriter().getNo() != loginUser.getNo()
    // && !loginUser.getEmail().equals("root@test.com"))) {
    // return;
    // }

    request.setAttribute("freeNo", no);

    // 내가 쓴 글인 경우
    // if (freeStudy.getWriter().getNo()==AuthLogInHandler.getLoginUser().getNo()) {
    while (true) {
      System.out.println("1. 참여 취소");
      System.out.println("0. 이전");
      System.out.println();

      int num = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
      System.out.println();

      if (num == 1) {
        request.getRequestDispatcher("/freeStudy/applyCancel").forward(request);
        return;

      } else if (num == 0) {
        return;

      } else {
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
  }
}
// }
