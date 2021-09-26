package com.studywithus.handler.study;

import java.util.HashMap;
import java.util.List;

import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class ChargeStudyPaymentListHandler extends AbstractStudyHandler {

	// 유료 스터디 결제내역 리스트 (회원 관점)
	List<Payment> chargePaymentList;

	// 각 회원의 참여 유료 스터디 리스트
	HashMap<String, List<Study>> participateChargeStudyMap;

	//	//	유료 스터디 결제자 내역 (멘토 관점)
	//	List<Member> chargeApplicantList;

	public ChargeStudyPaymentListHandler(List<Study> chargeStudyList, HashMap<String, List<Study>> participateChargeStudyMap) {
		super(chargeStudyList);
		this.participateChargeStudyMap = participateChargeStudyMap;
	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[유료 스터디 결제 내역 / 조회]\n");

		// 일치하는 값이 없을경우, 유료 스터디 결제 내역이 없다는 출력만 한 번만 출력되게 하기 위한 변수
		List<Study> participateStudies = participateChargeStudyMap.get(AuthLogInHandler.getLoginUser().getId()); 

		if (participateStudies ==  null) {
			System.out.println("유료 스터디 결제 내역이 없습니다.");
			return;
		} 
		for (Study participateStudy : participateStudies) {
			System.out.printf("제목: %s\n", participateStudy.getTitle());
			System.out.printf("멘토: %s\n", participateStudy.getWriter().getName());
			System.out.printf("가격: %s\n", participateStudy.getPrice());
		}
	}
}

