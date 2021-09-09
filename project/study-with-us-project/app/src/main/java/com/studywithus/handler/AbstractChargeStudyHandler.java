package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public abstract class AbstractChargeStudyHandler implements Command {

  protected List<Study> chargeStudyList;
  protected List<Study> paymentStudyList;
  protected List<Study> chargeInterestList;
  protected List<Study> chargeDetailRequestList;

  public AbstractChargeStudyHandler(List<Study> chargeStudyList) {
    this.chargeStudyList = chargeStudyList;
  }

  public AbstractChargeStudyHandler(List<Study> paymentStudyList, String a) {
    this.paymentStudyList = paymentStudyList;
  }

  public AbstractChargeStudyHandler(List<Study> chargeStudyList, List<Study> chargeInterestList, List<Study> paymentStudyList) {
    this.chargeStudyList = chargeStudyList;
    this.chargeInterestList = chargeInterestList;
    this.paymentStudyList = paymentStudyList;
  }

  public AbstractChargeStudyHandler(List<Study> chargeDetailRequestList, int nothing) {
    this.chargeDetailRequestList = chargeDetailRequestList;
  }

  public AbstractChargeStudyHandler(List<Study> chargeStudyList, List<Study> chargeDetailRequestList, int nothing) {
    this.chargeStudyList = chargeStudyList;
    this.chargeDetailRequestList = chargeDetailRequestList;
  }

  @Override
  public  void execute(){
  }

  // 유료 스터디 번호 조회
  protected Study findByNo(int no) {
    for (Study study : chargeStudyList) {
      if (study.getNo() == no) {
        return study;
      }
    }
    return null;
  }
}
