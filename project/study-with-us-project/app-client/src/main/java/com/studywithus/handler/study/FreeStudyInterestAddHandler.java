package com.studywithus.handler.study;

import java.util.HashMap;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class FreeStudyInterestAddHandler implements Command {

  RequestAgent requestAgent;

  public FreeStudyInterestAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 상세보기 / 관심 목록 / 추가]\n");
    int no = (int) request.getAttribute("freeNo");

    HashMap<String, String> params = new HashMap<>();
    params.put("freeNo", String.valueOf(no));

    requestAgent.request("freeStudy.interest.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Study freeStudyInterest = new Study();

    while (true) {
      String input = Prompt.inputString("무료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("무료 스터디 관심 목록 추가를 취소하였습니다.\n");
        return;

      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    // List<Member> likeMember = freeInterest.getLikeMembers();
    // likeMember.add(AuthLogInHandler.getLoginUser());
    // freeStudyInterest.setLikeMembers(AuthLogInHandler.getLoginUser());

    requestAgent.request("freeStudy.interest.insert", freeStudyInterest);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      requestAgent.request("member.interest.insert", params);
      System.out.println("무료 스터디 관심 목록에 추가되었습니다.");
    } else {
      System.out.println("무료 스터디 관심 목록에 추가되지 않았습니다.");
    }

    // System.out.println();
    // System.out.println("무료 스터디 관심 목록에 추가되었습니다.");
  }
}
