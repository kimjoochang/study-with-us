package com.studywithus.handler.study;

import java.util.List;

import com.studywithus.domain.Member;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class ChargeStudyPaymentListHandler extends AbstractStudyHandler {

	// 유료 스터디 결제내역 리스트 (회원 관점)
	List<Payment> chargePaymentList;

	// 유료 스터디 결제자 내역 (멘토 관점)
	//	List<Member> chargeApplicantList;


	public ChargeStudyPaymentListHandler(List<Study> chargeStudyList) {
		super(chargeStudyList);
	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[유료 스터디 결제 내역 / 조회]\n");

		int type = 0;

		for (Study chargeStudy : studyList) {
			for (Member member : chargeStudy.getApplicants()) {
				if (member.getId().equals(AuthLogInHandler.getLoginUser().getId())) {
					type = 1;
					System.out.printf("제목: %s\n", chargeStudy.getTitle());
					System.out.printf("멘토: %s\n", chargeStudy.getWriter().getName());

					System.out.printf("가격: %s\n", chargeStudy.getPrice());
				} else {
					if (type == 1) {
						continue;
					} else {
						type = 0;
					}
				}
			}
		}
		if (type == 0) {
			System.out.println("유료 스터디 결제 내역이 없습니다.");
		}
	}
}
