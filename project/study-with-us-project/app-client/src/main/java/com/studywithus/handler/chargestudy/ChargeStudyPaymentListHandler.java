package com.studywithus.handler.chargestudy;

import java.util.Collection;
import com.studywithus.dao.PaymentDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class ChargeStudyPaymentListHandler implements Command {

  PaymentDao paymentDao;
  StudyDao chargeStudyDao;

  public ChargeStudyPaymentListHandler(PaymentDao paymentDao, StudyDao chargeStudyDao) {
    this.paymentDao = paymentDao;
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 결제 내역 / 조회]\n");

    Collection<Payment> paymentList = paymentDao.findAll(AuthLogInHandler.getLoginUser().getNo());

    for (Payment payment : paymentList) {
      Study study = chargeStudyDao.findByNo(payment.getStudyNo());
      System.out.printf("결제한 스터디 번호: %s\n", study.getNo());
      System.out.printf("결제한 스터디 제목: %s\n", study.getTitle());
      System.out.printf("결제한 스터디 멘토: %s\n", study.getWriter().getName());
      System.out.printf("결제한 스터디 가격: %s\n", study.getPrice());
      System.out.printf("결제일: %s\n", payment.getPaymentDate());
      System.out.println();
    }
  }
}
