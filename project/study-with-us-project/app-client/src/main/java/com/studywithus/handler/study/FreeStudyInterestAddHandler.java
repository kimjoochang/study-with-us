package com.studywithus.handler.study;

import java.util.Collection;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class FreeStudyInterestAddHandler implements Command {

  RequestAgent requestAgent;

  public FreeStudyInterestAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이페이지 / 무료 스터디 / 관심 목록 / 추가]\n");
    int no = (int) request.getAttribute("freeNo");

    // Study freeInterest = findByNo(no);

    requestAgent.request("freeStudy.selectList", null);

    // if (freeInterest == null) {
    // System.out.println("해당 번호의 게시글이 없습니다.");
    // return;
    // }

    while (true) {
      String input = Prompt.inputString("무료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("무료 스터디 관심 목록 추가를 취소하였습니다.");
        return;

      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    List<Member> likeUserList = freeInterest.getLikeMembers();
    likeUserList.add(AuthLogInHandler.getLoginUser());
    freeInterest.setLikeMembers(likeUserList);

    Collection<Study> studyList = requestAgent.getObjects(Study.class);

    requestAgent.request("freeStudy.insert", freeStudy);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 저장 실패!");
      return;
    }

    System.out.println();
    System.out.println("무료 스터디 관심 목록에 추가되었습니다.");
  }
}
