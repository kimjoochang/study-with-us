package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.ChargeStudy;


public abstract class AbstractChargeStudyHandler implements Command {

	protected List<ChargeStudy> chargeStudyList;

	public AbstractChargeStudyHandler(List<ChargeStudy> chargeStudyList) {
		this.chargeStudyList = chargeStudyList;
	}

	// 유료 스터디 번호 조회
	protected ChargeStudy findByNo(int no) {
		for (ChargeStudy chargeStudy : chargeStudyList) {
			if (chargeStudy.getNo() == no) {
				return chargeStudy;
			}
		}
		return null;
	}
}
