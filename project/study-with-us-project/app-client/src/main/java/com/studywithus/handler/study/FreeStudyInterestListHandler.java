package com.studywithus.handler.study;

import java.util.Collection;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;

public class FreeStudyInterestListHandler implements Command {

  RequestAgent requestAgent;

  public FreeStudyInterestListHandler(RequestAgent requestAgent) {
    // super(freeStudyList);
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이페이지 / 무료 스터디 / 관심 목록 / 조회]\n");

    requestAgent.request("freeStudy.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("무료 스터디 관심목록 조회 실패!");
      return;
    }

    Collection<Study> freeStudyList = requestAgent.getObjects(Study.class);
    int type = 0; // 일치하는 값 X -> 게시글 없다는 출력문 한 번만 출력

    for (Study freeStudy : freeStudyList) {
      if (freeStudy.getLikeMembers().isEmpty()) {
        type = 0; // 관심목록 X
        continue;
      }

      for (Member likeMember : freeStudy.getLikeMembers()) {
        // 관심목록 추가한 회원 == 로그인한 회원
        if (likeMember.getEmail().equals(AuthLogInHandler.loginUser.getEmail())) {
          type = 1; // 관심목록 O

          // 온라인 스터디
          if (freeStudy.getOnOffLine() == 1) {
            System.out.printf(
                "[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
                freeStudy.getNo(), freeStudy.getTitle(), freeStudy.getWriter().getName(),
                freeStudy.getONLINE(), freeStudy.getMembers().size(), freeStudy.getMaxMembers(),
                freeStudy.getRegisteredDate(), freeStudy.getViewCount(),
                freeStudy.getLikeMembers().size());

            // 오프라인 스터디
          } else {
            System.out.printf(
                "[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 지역 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
                freeStudy.getNo(), freeStudy.getTitle(), freeStudy.getWriter().getName(),
                freeStudy.getOFFLINE(), freeStudy.getArea(), freeStudy.getMembers().size(),
                freeStudy.getMaxMembers(), freeStudy.getRegisteredDate(), freeStudy.getViewCount(),
                freeStudy.getLikeMembers().size());
          }

        } else {
          // 관심목록 O
          if (type == 1) {
            continue;

            // 관심목록 X
          } else {
            type = 0;
          }
        }
      }
    }

    // 관심목록 X
    if (type == 0) {
      System.out.println("무료 스터디 관심목록이 존재하지 않습니다.\n");
    }
    System.out.println();
  }
}
