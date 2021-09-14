package com.studywithus.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

public class Payment implements Serializable {
  private static final long serialVersionUID = 1L;

  private String paidChargeStudy; // 결제 스터디명
  private String paymentMethod; // 결제 수단
  private Date registeredDate; // 결제일
  private List<Study> paymentChargeStudyList; // 결제스터디 목록

  public String getPaidChargeStudy() {
    return paidChargeStudy;
  }

  public void setPaidChargeStudy(String paidChargeStudy) {
    this.paidChargeStudy = paidChargeStudy;
  }

  public String getPaymentMethod() {
    return paymentMethod;
  }

  public void setPaymentMethod(String paymentMethod) {
    this.paymentMethod = paymentMethod;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public List<Study> getPaymentChargeStudyList() {
    return paymentChargeStudyList;
  }

  public void setPaymentChargeStudyList(List<Study> paymentChargeStudyList) {
    this.paymentChargeStudyList = paymentChargeStudyList;
  }
}
