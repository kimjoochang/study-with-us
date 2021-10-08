package com.studywithus.handler.freestudy;

import java.util.Collection;

import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;

public class FreeStudyListHandler implements Command {

	RequestAgent requestAgent;

	public FreeStudyListHandler(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[무료 스터디 / 조회]\n");

		requestAgent.request("freeStudy.selectList", null);

		// if (studyList.isEmpty()) {
		// System.out.println("무료 스터디 게시글이 존재하지 않습니다.");
		// return;
		// }

		if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			System.out.println("목록 조회 실패!");
			return;
		}

		Collection<Study> studyList = requestAgent.getObjects(Study.class);

		for (Study freeStudy : studyList) {
			if (freeStudy.getOnOffLine() == 1) {
				System.out.printf(
						"[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
						freeStudy.getNo(), freeStudy.getTitle(), freeStudy.getWriter().getName(),
						freeStudy.getONLINE(), freeStudy.getMembers().size(), freeStudy.getMaxMembers(),
						freeStudy.getRegisteredDate(), freeStudy.getViewCount(),
						freeStudy.getLikeMembers().size());
			} else {
				System.out.printf(
						"[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 지역 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
						freeStudy.getNo(), freeStudy.getTitle(), freeStudy.getWriter().getName(),
						freeStudy.getOFFLINE(), freeStudy.getArea(), freeStudy.getMembers().size(),
						freeStudy.getMaxMembers(), freeStudy.getRegisteredDate(), freeStudy.getViewCount(),
						freeStudy.getLikeMembers().size());
			}
		}
	}
}
