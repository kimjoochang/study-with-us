package com.studywithus.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Interest implements Serializable{

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
