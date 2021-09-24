package com.studywithus.handler.study;

import java.util.HashMap;
import java.util.List;

import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class ParticipateChargeStudyListHandler implements Command {

	// 각 회원의 참여 유료 스터디 리스트
	HashMap<String, List<Study>> participateChargeStudyMap;

	public ParticipateChargeStudyListHandler(HashMap<String, List<Study>> participateChargeStudyMap) {
		this.participateChargeStudyMap = participateChargeStudyMap;
	}

	@Override
	public void execute(CommandRequest request) { 
		/* 해쉬맵의 value값을 myParticipatedFreeStudy에 담음 
		 * 전역변수로 둘 경우 App 실행 시 getLoginUser() nullPointer 에러뜸 */
		List<Study> myParticipatedChargeStudy = participateChargeStudyMap.get(AuthLogInHandler.getLoginUser().getId());

		System.out.println("[마이 페이지 / 내가 참여한 유료 스터디]\n");

		if (participateChargeStudyMap.isEmpty() == true) {
			System.out.println("유료 스터디 게시글이 존재하지 않습니다.\n");
			return;
		}

		// 회원 아이디로 값 호출
		for (Study chargeStudy : myParticipatedChargeStudy) {
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
