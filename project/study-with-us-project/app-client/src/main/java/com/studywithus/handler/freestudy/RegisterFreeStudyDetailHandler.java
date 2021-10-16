package com.studywithus.handler.freestudy;

import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class RegisterFreeStudyDetailHandler implements Command {

	FreeStudyDao FreeStudyDao;

	public RegisterFreeStudyDetailHandler(FreeStudyDao FreeStudyDao) {
		this.FreeStudyDao = FreeStudyDao;
	}

	@Override
	public void execute(CommandRequest request) throws Exception  {

		System.out.println("[마이 페이지 / 나의 활동 / 나의 스터디 / 내가 생성한 무료 스터디 / 상세보기]\n");

		int no = Prompt.inputInt("번호를 입력하세요. > ");

		Study FreeStudy = FreeStudyDao.findByNo(no);

		if (!FreeStudy.getWriter().getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
			System.out.println("번호에 해당하는 내가 생성한 무료 스터디가 없습니다.");
			return;
		}

		FreeStudy.setViewCount(FreeStudy.getViewCount() + 1);

		System.out.printf("제목: %s\n", FreeStudy.getTitle());
		System.out.printf("멘토: %s\n", FreeStudy.getWriter().getName());

		System.out.printf("설명: %s\n", FreeStudy.getContent());
		System.out.printf("지역: %s\n", FreeStudy.getArea());
		System.out.printf("가격: %s\n", FreeStudy.getPrice());
		System.out.printf("등록일: %s\n", FreeStudy.getRegisteredDate());

		System.out.printf("모집인원 = %d / %d\n", FreeStudy.getMembers().size(), FreeStudy.getMaxMembers());
		System.out.printf("조회수: %d\n", FreeStudy.getViewCount());
		System.out.printf("좋아요수: %d\n", FreeStudy.getLikeMembers().size());
		System.out.println();

		request.setAttribute("FreeNo", no);

		System.out.println("1. 수정"); 

		int changableMenu = 0; // 메뉴 출력 다르게 하기 위한 변수
		if(FreeStudy.isDeleteRequest() == false) {
			System.out.println("2. 삭제 요청");
			changableMenu = 1;
		} else {
			System.out.println("2. 삭제 요청 취소");
			changableMenu = 2;
		}

		System.out.println("0. 이전\n");

		int input = Prompt.inputInt("메뉴 번호를 선택하세요. > "); 
		System.out.println();

		if (input == 1) {
			request.getRequestDispatcher("/FreeStudy/update").forward(request);
		} else if (input == 2 && changableMenu == 1) {
			request.getRequestDispatcher("/FreeStudy/deleteRequest").forward(request);
		}  else if (input == 2 && changableMenu == 2) {
			request.getRequestDispatcher("/FreeStudy/deleteRequestCancel").forward(request);
		} else if (input == 0) {
			return;
		}
	}

}