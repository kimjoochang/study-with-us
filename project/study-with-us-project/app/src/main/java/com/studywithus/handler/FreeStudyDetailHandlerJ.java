package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class FreeStudyDetailHandlerJ extends AbstractStudyHandler {

  // 무료 스터디 신청 리스트 (회원 관점)
  List<Study> freeApplicationList;

  // 무료 스터디 관심목록 리스트 (회원 관점)
  List<Study> freeInterestList;

  public FreeStudyDetailHandlerJ(List<Study> freeStudyList, List<Study> freeApplicationList, List<Study> freeInterestList) {
    super(freeStudyList);
    this.freeApplicationList = freeApplicationList;
    this.freeInterestList = freeInterestList;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 상세보기]\n");
    int no = Prompt.inputInt("번호? ");

    Study freeStudy = findByNo(no);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", freeStudy.getTitle());
    System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

    if (freeStudy.getArea() != null) {
      System.out.printf("지역: %s\n", freeStudy.getArea());
    }

    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());
    System.out.println();

    Member loginUser = AuthLoginHandler.getLoginUser(); 
    if (loginUser == null || freeStudy.getWriter().getId() != loginUser.getId()) {
      return;
    }

    // FreeStudyUpdateHandler나 FreeStudyDeleteHandler를 실행할 때 
    // 게시글 번호를 사용할 수 있도록 CommandRequest에 보관한다.
    request.setAttribute("no", no);

    while (true) {
      String input = Prompt.inputString("변경(U), 삭제(D), 이전(0)>");
      switch (input) {
        case "U":
        case "u":
          request.getRequestDispatcher("/freeStudy/update").forward(request);
          return;
        case "D":
        case "d":
          request.getRequestDispatcher("/freeStudy/delete").forward(request);
          return;
        case "0":
          return;
        default:
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }
  }
}
