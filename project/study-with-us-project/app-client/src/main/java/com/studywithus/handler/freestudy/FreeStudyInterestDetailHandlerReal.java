package com.studywithus.handler.freestudy;

import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class FreeStudyInterestDetailHandlerReal implements Command {

  FreeStudyDao freeStudyDao;

  public FreeStudyInterestDetailHandlerReal(FreeStudyDao freeStudyDao) {
    this.freeStudyDao = freeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[STUDY WITH US / 마이페이지 / 나의 관심목록 / 무료 스터디 관심목록 / 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    // Study freeStudy = findByNo(no);

    // HashMap<String, String> params = new HashMap<>();
    // params.put("no", String.valueOf(no));

    Study freeStudy = freeStudyDao.findByNo(no);

    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // System.out.println("해당 번호의 무료 스터디가 없습니다.");
    // return;
    // }

    // Study freeStudy = requestAgent.getObject(Study.class);
    freeStudy.setViewCount(freeStudy.getViewCount() + 1);

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

    // System.out.println("[STUDY WITH US / 마이페이지 / 나의 관심목록 / 무료 스터디 관심목록 / 상세보기 / 삭제]\n");
    // int type = 0; // 일치하는 값 X -> 게시글 없다는 출력문 한 번만 출력
    // int no = Prompt.inputInt("무료 스터디 번호를 입력하세요. > ");
    //
    // Study freeInterest = findByNo(no);
    //
    // HashMap<String, String> params = new HashMap<>();
    // params.put("no", String.valueOf(no));
    //
    // requestAgent.request("freeStudy.selectOne", params);
    //
    // if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
    // System.out.println("무료 스터디 상세보기 실패!");
    // System.out.println(requestAgent.getObject(Study.class));
    // return;
    // }
    //
    // Study freeStudy = requestAgent.getObject(Study.class);
    //
    // if (request.getAttribute("freeNo") == null) {
    //
    // for (Member likeMember : freeStudy.getLikeMembers()) {
    // if (likeMember.getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
    // type = 1;
    // break;
    // }
    // }
    //
    // if (type == 0) {
    // System.out.println("해당 번호의 관심 목록이 없습니다.");
    // return;
    // }
    //
    // while (true) {
    // String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
    //
    // if (input.equalsIgnoreCase("n") || input.length() == 0) {
    // System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
    // return;
    //
    // } else if (!input.equalsIgnoreCase("y")) {
    // System.out.println("다시 입력하세요.\n");
    // continue;
    //
    // } else {
    // break;
    // }
    // }
    // } else {
    // int no = (int) request.getAttribute("freeNo");
    //
    // Study freeInterest = findByNo(no);
    //
    // if (freeStudy == null) {
    // System.out.println("해당 번호의 게시글이 없습니다.");
    // return;
    // }
    //
    // if (freeStudy.getLikeMembers().isEmpty()) {
    // System.out.println("무료 스터디 관심목록이 존재하지 않습니다.\n");
    // return;
    // }
    //
    // while (true) {
    // String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
    //
    // if (input.equalsIgnoreCase("n") || input.length() == 0) {
    // System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
    // return;
    //
    // } else if (input.equalsIgnoreCase("y")) {
    // freeStudy.getLikeMembers().remove(AuthLogInHandler.getLoginUser());
    // requestAgent.request("freeStudy.update", freeStudy);
    //
    // if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
    // System.out.println("무료 스터디 관심 목록 삭제 성공!");
    //
    // } else {
    // System.out.println("무료 스터디 관심 목록 삭제 실패!");
    // }
    //
    // } else {
    // System.out.println("다시 입력하세요.\n");
    // continue;
    // }
    // }
    // }
  }
}
