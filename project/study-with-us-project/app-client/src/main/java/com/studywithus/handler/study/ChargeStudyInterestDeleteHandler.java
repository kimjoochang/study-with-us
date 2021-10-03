package com.studywithus.handler.study;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

// [09.24 merge by 제이] App에서 재확인 필요

public class ChargeStudyInterestDeleteHandler implements Command {

  RequestAgent requestAgent;

  public ChargeStudyInterestDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이페이지 / 유료 스터디 / 관심 목록 / 삭제]\n");

    int type = 0;

    if (request.getAttribute("chargeNo") == null) {

      int no = Prompt.inputInt("삭제할 관심목록 번호를 입력하세요. > ");

      HashMap<String,String> params = new HashMap<>();
      params.put("no", String.valueOf(no));

      requestAgent.request("chargeStudy.selectOne", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println(requestAgent.getObject(String.class));
        return;
      }

      Study chargeStudy = requestAgent.getObject(Study.class);

      for (String likeMemberEmail : chargeStudy.getLikeMembersEmail()) {

        if (likeMemberEmail.equals(AuthLogInHandler.getLoginUser().getEmail())) {
          type = 1;
          break;
        }
      }

      if (type == 0) {
        System.out.println("해당 번호의 관심목록이 없습니다.\n");
        return;
      }

      while (true) {
        String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

        if (input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("유료 스터디 관심 목록 삭제를 취소하였습니다.\n");
          return;

        } else if (!input.equalsIgnoreCase("y")) {
          System.out.println("다시 입력하세요.\n");
          continue;

        } else {
          break;
        }
      }

      List<String> likeMemberEmail = chargeStudy.getLikeMembersEmail();
      likeMemberEmail.remove(AuthLogInHandler.getLoginUser().getEmail());
      chargeStudy.setLikeMembersEmail(likeMemberEmail);

      requestAgent.request("chargeStudy.update", chargeStudy);

      System.out.println();
      System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");

    } else {
      int no = (int) request.getAttribute("chargeNo");

      HashMap<String,String> params = new HashMap<>();
      params.put("no", String.valueOf(no));

      requestAgent.request("chargeStudy.selectOne", params);

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println(requestAgent.getObject(String.class));
        return;
      }

      Study chargeStudy = requestAgent.getObject(Study.class);

      if (chargeStudy.getLikeMembersEmail().isEmpty()) {
        System.out.println("유료 스터디 관심목록이 존재하지 않습니다.\n");
        return;
      }

      while (true) {
        String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

        if (input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("유료 스터디 관심 목록을 취소하였습니다.\n");
          return;

        } else if (!input.equalsIgnoreCase("y")) {
          System.out.println("다시 입력하세오.\n");
          continue;

        } else {
          break;
        }
      }

      List<String> likeMemberEmail = chargeStudy.getLikeMembersEmail();
      likeMemberEmail.remove(AuthLogInHandler.getLoginUser().getEmail());
      chargeStudy.setLikeMembersEmail(likeMemberEmail);

      requestAgent.request("chargeStudy.update", chargeStudy);

      System.out.println();
      System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");
    }
  }
}
