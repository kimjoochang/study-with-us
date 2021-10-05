package com.studywithus.handler.study;

import java.util.Collection;
import com.studywithus.domain.Payment;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;

public class ChargeStudyPaymentListHandler implements Command {

  RequestAgent requestAgent;

  public ChargeStudyPaymentListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 결제 내역 / 조회]\n");

    requestAgent.request("payment.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("결제 내역이 존재하지 않습니다.");
      return;
    }

    Collection<Payment> paymentList = requestAgent.getObjects(Payment.class);

    if (paymentList ==  null) {
      System.out.println("유료 스터디 결제 내역이 없습니다.");
      return;
    } 


    for (Payment payment : paymentList) {
      if (payment.getMenteeEmail().equals(AuthLogInHandler.getLoginUser().getEmail())
          && payment.isVisible()) {
        System.out.printf("결제한 스터디 번호: %s\n", payment.getPaidStudyNo());
        System.out.printf("결제한 스터디 제목: %s\n", payment.getTitle());
        System.out.printf("결제한 스터디 멘토: %s\n", payment.getMentorName());
        System.out.printf("결제한 스터디 가격: %s\n", payment.getPrice());
        System.out.printf("결제일: %s\n", payment.getPaymentDate());
      }
    }
  }
}

