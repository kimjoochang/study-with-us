package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public class PaymentStudyListHandler extends AbstractChargeStudyHandler{

  List<Study> paymentStudyList;

  public PaymentStudyListHandler(List<Study> paymentStudyList) {
    super(paymentStudyList, "");
  }

  @Override
  public void execute() {
    System.out.println("[메인 / 마이페이지 / 스터디 결제 내역]\n");

    for (Study study : paymentStudyList) {
      System.out.println();
      System.out.printf("%d, %s, %d \n", study.getNo(),study.getTitle(), study.getPrice());
    }
  }
}
