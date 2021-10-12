package com.studywithus.handler.chargestudy;

import java.util.Collection;
import com.studywithus.dao.PaymentDao;
import com.studywithus.domain.Payment;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class ChargeStudyPaymentListHandler implements Command {

  PaymentDao paymentDao;

  public ChargeStudyPaymentListHandler(PaymentDao paymentDao) {
    this.paymentDao = paymentDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 결제 내역 / 조회]\n");

    Collection<Payment> paymentList = paymentDao.findAll();

    for (Payment payment : paymentList) {
      if (payment.getMenteeEmail().equals(AuthLogInHandler.getLoginUser().getEmail())
          && payment.isVisible()) {
        System.out.printf("결제한 스터디 번호: %s\n", payment.getPaidStudyNo());
        System.out.printf("결제한 스터디 제목: %s\n", payment.getTitle());
        System.out.printf("결제한 스터디 멘토: %s\n", payment.getMentorName());
        System.out.printf("결제한 스터디 가격: %s\n", payment.getPrice());
        System.out.printf("결제일: %s\n", payment.getPaymentDate());
        System.out.printf("출력여부: %s\n", payment.isVisible());
        System.out.println();
      }
    }
  }
}

