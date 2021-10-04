package com.studywithus.handler.study;

import java.util.HashMap;
import com.studywithus.domain.Payment;
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

    params.put("no", String.valueOf(no));
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
        System.out.println("다시 입력하시오.\n");
        continue;

      } else {
        System.out.println();
        System.out.println("결제 취소가 완료되었습니다.");
        payment.setVisible(false);

        requestAgent.request("payment.update", payment);
        break;
      }
    }

  }
}
