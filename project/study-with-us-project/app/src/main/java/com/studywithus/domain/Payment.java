package com.studywithus.domain;

import java.sql.Date;

public class Payment {
	private String paidChargeStudy; // 결제 스터디명
	private String paymentMethod; // 결제 수단 // 보류
	private Date registeredDate; // 결제일

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
}
