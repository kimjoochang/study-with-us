package com.studywithus.domain;

public class Interest {

  private int no;
  private FreeStudy freeInterest;
  private ChargeStudy chargeInterest;

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public FreeStudy getFreeInterest() {
    return freeInterest;
  }

  public void setFreeInterest(FreeStudy freeInterest) {
    this.freeInterest = freeInterest;
  }

  public ChargeStudy getChargeInterest() {
    return chargeInterest;
  }

  public void setChargeInterest(ChargeStudy chargeInterest) {
    this.chargeInterest = chargeInterest;
  }
}
