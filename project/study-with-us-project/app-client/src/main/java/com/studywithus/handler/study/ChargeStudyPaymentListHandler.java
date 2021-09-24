package com.studywithus.handler.study;

import java.util.HashMap;
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
	List<Member> chargeApplicantList;

	// 각 회원의 참여 유료 스터디 리스트
	HashMap<String, List<Study>> participateChargeStudyMap;
	List<Study> participateChargeStudyList;

	public ChargeStudyPaymentListHandler(List<Study> chargeStudyList, List<Payment> chargePaymentList, List<Member> chargeApplicantList, HashMap<String, List<Study>> participateChargeStudyMap) {
		super(chargeStudyList);
		this.chargePaymentList = chargePaymentList;
		this.chargeApplicantList = chargeApplicantList;
		this.participateChargeStudyMap = participateChargeStudyMap;
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

					System.out.printf("설명: %s\n", chargeStudy.getContent());
					System.out.printf("지역: %s\n", chargeStudy.getArea());
					System.out.printf("가격: %s\n", chargeStudy.getPrice());
					System.out.printf("등록일: %s\n", chargeStudy.getRegisteredDate());

					System.out.printf("모집인원 = %d / %d\n", chargeStudy.getMembers().size(), chargeStudy.getMaxMembers());
					chargeStudy.setViewCount(chargeStudy.getViewCount() + 1);
					System.out.printf("조회수: %d\n", chargeStudy.getViewCount());
					System.out.printf("좋아요수: %d\n", chargeStudy.getLikeMembers().size());
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
