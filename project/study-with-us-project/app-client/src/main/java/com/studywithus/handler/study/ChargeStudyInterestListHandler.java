package com.studywithus.handler.study;

import java.util.Collection;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;

public class ChargeStudyInterestListHandler implements Command {

  RequestAgent requestAgent;

  public ChargeStudyInterestListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 관심목록 / 조회]\n");

    requestAgent.request("chargeStudy.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("유료 스터디 게시글이 존재하지 않습니다.");
      return;
    }

    Collection<Study> chargeStudyList = requestAgent.getObjects(Study.class);

    // 일치하는 값이 없을 경우, 게시글 없다는 출력 한번만 하기 위한 변수
    int type = 0;

    // 기존 스터디 리스트 가져와서 하나씩 검색
    for (Study chargeStudy : chargeStudyList) {
      if (chargeStudy.getLikeMembersEmail().isEmpty()) {

        if (type == 2) {
          continue;
        } else {
          type = 1;
          continue;
        }
      }

      // 스터디 리스트에서 꺼낸 스터디의 좋아요 누른 회원 리스트를 가져와서 하나씩 검색
      for (String likeMemberEmail : chargeStudy.getLikeMembersEmail()) {
        // 좋아요 누른 회원 리스트에서 꺼낸 회원의 아이디와 로그인한 회원의 아이디가 같다면
        // 좋아요 누른 회원 리스트를 가지고 있는 스터디의 정보 출력
        if(likeMemberEmail.equals(AuthLogInHandler.loginUser.getEmail())) {
          type = 2; // 일치하는 값 있음
          System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
              chargeStudy.getNo(),
              chargeStudy.getTitle(),
              chargeStudy.getWriter().getName(),
              chargeStudy.getRegisteredDate(),
              chargeStudy.getMembers().size(),
              chargeStudy.getMaxMembers(),
              chargeStudy.getViewCount(),
              chargeStudy.getLikeMembers().size());
        } else {
          if (type == 2) {
            continue;
          } else {
            type = 1;
          }
        }
      }

    }

    if (type == 1) {
      System.out.println("유료 스터디 관심목록이 존재하지 않습니다.");
    }
    System.out.println(" ");
  }
}
