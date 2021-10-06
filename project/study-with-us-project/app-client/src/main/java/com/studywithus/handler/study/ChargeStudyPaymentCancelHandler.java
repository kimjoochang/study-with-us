package com.studywithus.handler.study;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ChargeStudyPaymentCancelHandler implements Command {

  RequestAgent requestAgent;

  public ChargeStudyPaymentCancelHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기 / 결제취소]\n");

    int no = (int) request.getAttribute("chargeNo");

    HashMap<String,String> params = new HashMap<>();

    // 스터디 객체 가져오기
    params.put("no", String.valueOf(no));
    requestAgent.request("chargeStudy.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    Study chargeStudy = requestAgent.getObject(Study.class);

    // payment 객체 가져오기
    params.put("email", AuthLogInHandler.getLoginUser().getEmail());

    requestAgent.request("payment.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    Payment payment = requestAgent.getObject(Payment.class);

    while (true) {
      String input = Prompt.inputString("유료 스터디를 결제를 취소 하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("유료 스터디 결제 취소가 취소되었습니다.");
        return;

      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("유효한 값을 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    requestAgent.request("payment.update", payment);

    List<String> menteeEmailList = chargeStudy.getMenteeEmailList();
    menteeEmailList.remove(AuthLogInHandler.getLoginUser().getEmail());
    chargeStudy.setMenteeEmailList(menteeEmailList);

    requestAgent.request("chargeStudy.update", chargeStudy);

    System.out.println();
    System.out.println("결제 취소가 완료되었습니다.");
    payment.setVisible(false);
    requestAgent.request("payment.update", payment);

  }
}
