package com.studywithus.handler.chargestudy;

import java.util.HashMap;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ChargeStudyPaymentDetailHandlerJJ implements Command {

  RequestAgent requestAgent;

  public ChargeStudyPaymentDetailHandlerJJ(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {

    System.out.println("[마이페이지 / 유료 스터디 결제내역 / 상세보기]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");

    HashMap<String, String> params = new HashMap<>();

    requestAgent.request("payment.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 결제내역이 없습니다.\n");
      return;
    }

    Payment payment = requestAgent.getObject(Payment.class);

    System.out.printf("결제한 스터디 번호: %s\n", payment.getPaidStudyNo());
    System.out.printf("결제한 스터디 제목: %s\n", payment.getTitle());
    System.out.printf("결제한 스터디 멘토: %s\n", payment.getMentorName());
    System.out.printf("결제한 스터디 가격: %s\n", payment.getPrice());
    System.out.printf("결제일: %s\n", payment.getPaymentDate());
    System.out.println();

    params.put("no", String.valueOf(no));
    request.setAttribute("no", no);
    //    
    //
    //    params.put("no", String.valueOf(payment.getPaidStudyNo()));
    //
    //    requestAgent.request("chargeStudy.selectOne", no);

    if (requestAgent.getObject(Study.class).getStudyStatus().equals("모집중")
        || requestAgent.getObject(Study.class).getStudyStatus().equals("진행중")) {

      while (true) {

        System.out.println("1. 결제 취소하기");
        System.out.println("0. 이전");
        int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");

        if (input == 1) {
          request.setAttribute("no", no);
          request.getRequestDispatcher("/chargeStudy/paymentCancel").forward(request);

        } else if (input == 0) {
          return;

        } else {
          System.out.println("존재하지 않는 메뉴 번호입니다.");
          continue;
        }
        return;
      }
    }

  }

}

