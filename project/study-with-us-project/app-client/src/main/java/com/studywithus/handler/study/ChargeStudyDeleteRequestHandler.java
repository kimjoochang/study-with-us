package com.studywithus.handler.study;

import java.util.HashMap;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ChargeStudyDeleteRequestHandler implements Command {

  RequestAgent requestAgent;

  public ChargeStudyDeleteRequestHandler( RequestAgent requestAgent)  {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 삭제 요청]\n");
    int no = (int) request.getAttribute("chargeNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("chargeStudy.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    Study chargeStudy = requestAgent.getObject(Study.class);

    String input = Prompt.inputString("정말 삭제 요청 하시겠습니까? (y/N) ");
    System.out.println();
    while (true) {
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("유료 스터디 삭제 요청을 취소하였습니다.\n");
        return;
      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하세요.\n");
        continue;
      } else {
        break;
      }
    }
    chargeStudy.setDeleteRequest(true);

    requestAgent.request("chargeStudy.update", chargeStudy);

    System.out.println("삭제 요청이 완료되었습니다.\n");
  }
}
