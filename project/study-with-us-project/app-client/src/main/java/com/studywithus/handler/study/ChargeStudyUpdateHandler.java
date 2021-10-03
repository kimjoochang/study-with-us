package com.studywithus.handler.study;

import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ChargeStudyUpdateHandler implements Command {

  RequestAgent requestAgent;

  public ChargeStudyUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;	
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 수정]");
    int no = (int) request.getAttribute("chargeNo");

    requestAgent.request("chargeStudy.selectOne", no);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Study chargeStudy = requestAgent.getObject(Study.class);

    String title = Prompt.inputString(String.format("[%s] 수정된 스터디 제목: ", chargeStudy.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정된 내용: ", chargeStudy.getContent()));

    System.out.println();

    while (true) {
      String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) "); 

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println("유료 스터디 수정을 취소하였습니다.\n");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        chargeStudy.setTitle(title);
        chargeStudy.setContent(content);

        requestAgent.request("chargeStudy.update", chargeStudy);

        if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
          System.out.println("유료 스터디 수정을 실패했습니다.");
          System.out.println(requestAgent.getObject(String.class));
          return;
        }

        System.out.println("유료 스터디를 수정하였습니다.\n");
        return;

      } else {
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
  }
}
