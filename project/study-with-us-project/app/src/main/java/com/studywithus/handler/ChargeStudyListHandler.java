package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.ChargeStudy;

public class ChargeStudyListHandler extends AbstractChargeStudyHandler{

	public ChargeStudyListHandler(List<ChargeStudy> chargeStudyList) {
		super(chargeStudyList);	
	}

	// 유료 스터디 조회
	@Override
	public void execute() {
		System.out.println("[유료 스터디 / 조회]\n");
		for (ChargeStudy chargeStudy : chargeStudyList) {
			System.out.printf("%d, %s, %s, %s, %d, %d\n", chargeStudy.getNo(), chargeStudy.getTitle(),
					chargeStudy.getWriter(), chargeStudy.getRegisteredDate(), chargeStudy.getViewCount(),
					chargeStudy.getLike());
		}
	}
}