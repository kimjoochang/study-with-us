package com.studywithus.handler.study;

import java.util.HashMap;
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
    System.out.println("[무료 스터디 / 상세보기 / 관심 목록 / 추가]\n");
    int no = (int) request.getAttribute("freeNo");

    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("freeStudy.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 무료 스터디가 없습니다.");
      return;
    }

    Study freeStudy = requestAgent.getObject(Study.class);

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

    freeStudy.getLikeMembers().add(AuthLogInHandler.getLoginUser());

    requestAgent.request("freeStudy.interest.insert", freeStudy);

    if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
      // requestAgent.request("member.interest.insert", params);
      System.out.println("무료 스터디 관심 목록 추가 성공!");

    } else {
      System.out.println("무료 스터디 관심 목록 추가 실패!");
    }

    // System.out.println();
    // System.out.println("무료 스터디 관심 목록에 추가되었습니다.");
  }
}
