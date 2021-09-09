package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ChargeStudy;

public class PaymentStudyListHandler extends AbstractChargeStudyHandler{

  List<ChargeStudy> paymentStudyList;

  public PaymentStudyListHandler(List<ChargeStudy> chargeStudyList, List<ChargeStudy> paymentStudyList) {
    super(chargeStudyList);
    this.paymentStudyList = paymentStudyList;
  }

  @Override
  public void execute() {
    System.out.println("[메인 / 마이페이지 / 스터디 결제 내역]\n");

    for (ChargeStudy chargeStudy : paymentStudyList) {
      System.out.println();
      System.out.printf("%d, %s, %d \n", chargeStudy.getNo(),chargeStudy.getTitle(), chargeStudy.getPrice());
    }
  }
}
