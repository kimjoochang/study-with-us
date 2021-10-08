package com.studywithus.handler.chargestudy;

import java.util.HashMap;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ChargeStudyInterestAddHandler implements Command {

  RequestAgent requestAgent;

  public ChargeStudyInterestAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이페이지 / 유료 스터디 / 관심 목록 / 추가]\n");
    int no = (int) request.getAttribute("chargeNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("chargeStudy.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    Study chargeStudy = requestAgent.getObject(Study.class);

    while(true) {
      String input = Prompt.inputString("유료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("유료 스터디 관심 목록 추가를 취소하였습니다.\n");
        return;
      }else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        // 유료 스터디 관심목록 리스트 (회원 관점)
        //        List<String> likeMemberEmail = chargeStudy.getLikeMembersEmail();
        //        likeMemberEmail.add(AuthLogInHandler.getLoginUser().getEmail());
        //        chargeStudy.setLikeMembersEmail(likeMemberEmail);

        chargeStudy.getLikeMembersEmail().add(AuthLogInHandler.getLoginUser().getEmail());

        requestAgent.request("chargeStudy.update", chargeStudy);

        System.out.println();
        System.out.println("유료 스터디 관심 목록에 추가되었습니다.\n");
        break;
      }
    }

  }
}
