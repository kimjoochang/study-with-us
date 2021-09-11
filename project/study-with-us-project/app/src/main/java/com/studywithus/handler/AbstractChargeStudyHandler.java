package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ChargeStudy;

public abstract class AbstractChargeStudyHandler implements Command {

  protected List<ChargeStudy> chargeStudyList;
  //  protected List<ChargeStudy> paymentStudyList;
  //  protected List<ChargeStudy> chargeInterestList;
  //  protected List<ChargeStudy> chargeDetailRequestList;

  public AbstractChargeStudyHandler(List<ChargeStudy> chargeStudyList) {
    this.chargeStudyList = chargeStudyList;
  }

  //
  //    public AbstractChargeStudyHandler(List<ChargeStudy> chargeStudyList,List<ChargeStudy> paymentStudyList) {
  //      this.paymentStudyList = paymentStudyList;
  //    }
  //
  //  public AbstractChargeStudyHandler(List<ChargeStudy> chargeStudyList, List<ChargeStudy> chargeInterestList) {
  //    this.chargeStudyList = chargeStudyList;
  //    this.chargeInterestList = chargeInterestList;
  //    this.paymentStudyList = paymentStudyList;
  //  }
  //
  //  public AbstractChargeStudyHandler(List<ChargeStudy> chargeDetailRequestList, int nothing) {
  //    this.chargeDetailRequestList = chargeDetailRequestList;
  //  }
  //
  //  public AbstractChargeStudyHandler(List<ChargeStudy> chargeStudyList, List<ChargeStudy> chargeDetailRequestList, int nothing) {
  //    this.chargeStudyList = chargeStudyList;
  //    this.chargeDetailRequestList = chargeDetailRequestList;
  //  }

  @Override
  public  void execute(){
  }

  // 유료 스터디 목록 내 번호 조회
  protected ChargeStudy findByNo(int no) {
    for (ChargeStudy chargeStudy : chargeStudyList) {
      if (chargeStudy.getNo() == no) {
        return chargeStudy;
      }
    }
    return null;
  }
}
