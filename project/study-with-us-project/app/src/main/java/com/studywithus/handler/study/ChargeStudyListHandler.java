package com.studywithus.handler.study;

import java.util.List;

import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;

public class ChargeStudyListHandler extends AbstractStudyHandler {

	public ChargeStudyListHandler(List<Study> chargeStudyList) {
		super(chargeStudyList);	
	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[유료 스터디 / 조회]\n");
		System.out.println();

		if (studyList.isEmpty() == true) {
			System.out.println("유료 스터디 게시글이 존재하지 않습니다.\n");
			return;
		}

		for (Study chargeStudy : studyList) {
			System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
					chargeStudy.getNo(),
					chargeStudy.getTitle(),
					chargeStudy.getWriter().getName(),
					chargeStudy.getRegisteredDate(),
					chargeStudy.getMembers().size(),
					chargeStudy.getMaxMembers(),
					chargeStudy.getViewCount(),
					chargeStudy.getLikeMembers().size());
		}
		System.out.println();
	}
}
