package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ChargeStudy;

public class ChargeStudyDeletedListHandler extends AbstractChargeStudyHandler{

  public ChargeStudyDeletedListHandler( List<ChargeStudy> chargeDetailRequestList, int nothing) {
    super(chargeDetailRequestList, 1);
  }

  @Override
  public void execute() {
    System.out.println("[스터디 삭제 요청 내역 / 조회]\n");
    System.out.println("-----------------------------");
    for(ChargeStudy study : chargeDetailRequestList) {
      System.out.println("스터디 제목 : " + study.getTitle());
      System.out.println("멘토 이름 : " + study.getWriter());
      System.out.println("-----------------------------");
    }
  }

}
