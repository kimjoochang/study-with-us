package com.studywithus.servlet.FreeStudy;

import java.util.Collection;

import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.StudyStatusHelper;

public class FreeStudyListHandler implements Command {

	StudyDao FreeStudyDao;

	public FreeStudyListHandler(StudyDao FreeStudyDao) {
		this.FreeStudyDao = FreeStudyDao;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[무료 스터디 / 조회]\n");

		Collection<Study> FreeStudyList = FreeStudyDao.findAll();

		if (FreeStudyList.isEmpty()) {
			System.out.println("무료 스터디 게시글이 존재하지 않습니다.");
			return;
		}

		for (Study FreeStudy : FreeStudyList) {
			String status = StudyStatusHelper.studyStatus(FreeStudy);
			System.out.printf
			("[번호 = %d, 제목 = %s, 설명 = %s, 지역 = %s, 가격 = %d, 멘토 = %s, 시작일 = %s, 종료일 = %s,"
					+ " 스터디 진행상태 = %s, 등록일 = %s, 모집인원 = %d / %d, 조회수 = %d, 좋아요 = %d]\n",
					FreeStudy.getNo(),
					FreeStudy.getTitle(),
					FreeStudy.getContent(),
					FreeStudy.getArea(),
					FreeStudy.getPrice(),
					FreeStudy.getWriter().getName(),
					FreeStudy.getStartDate(),
					FreeStudy.getEndDate(),
					status,
					FreeStudy.getRegisteredDate(),
					FreeStudy.getMembers(),
					FreeStudy.getMaxMembers(),
					FreeStudy.getViewCount(),
					FreeStudy.getLikes());
			System.out.println();
		}
	}
}
