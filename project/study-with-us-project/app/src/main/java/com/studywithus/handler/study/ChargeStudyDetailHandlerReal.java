package com.studywithus.handler.study;

import java.util.List;

import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyDetailHandlerReal extends AbstractStudyHandler {

	//	Study chargeStudy;
	//	List<Study> chargeInterestList; // 유료 스터디 관심목록 리스트 (회원 관점)

	public ChargeStudyDetailHandlerReal(List<Study> chargeStudyList) {
		super(chargeStudyList);
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[유료 스터디 / 상세보기]\n");

		int no = Prompt.inputInt("번호? ");
		Study chargeStudy = findByNo(no);

		if (chargeStudy == null) {
			System.out.println();
			System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
			return;
		}

		chargeStudy.setViewCount(chargeStudy.getViewCount() + 1);

		System.out.printf("제목: %s\n", chargeStudy.getTitle());
		System.out.printf("멘토: %s\n", chargeStudy.getWriter().getName());

		System.out.printf("설명: %s\n", chargeStudy.getContent());
		System.out.printf("지역: %s\n", chargeStudy.getArea());
		System.out.printf("가격: %s\n", chargeStudy.getPrice());
		System.out.printf("등록일: %s\n", chargeStudy.getRegisteredDate());

		System.out.printf("모집인원 = %d / %d\n", chargeStudy.getMembers().size(), chargeStudy.getMaxMembers());
		System.out.printf("조회수: %d\n", chargeStudy.getViewCount());
		System.out.printf("좋아요수: %d\n", chargeStudy.getLikeMembers().size());
		System.out.println();

		Member loginUser = AuthLogInHandler.getLoginUser();
		if (loginUser == null || chargeStudy.getWriter() != loginUser) {
			// return;
		}

		// ChargeStudyUpdateHandler나 ChargeStudyDeleteHandler를 실행할 때 
		// 게시글 번호를 사용할 수 있도록 CommandRequest에 보관한다.
		request.setAttribute("ChargeNo", no);

		while (true) {
			// 본인이 작성한 글인 경우
			if (chargeStudy.getWriter() == loginUser) {
				System.out.println("1. 수정");
				System.out.println("2. 삭제");
				System.out.println("0. 이전\n");

				int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");

				if (input == 1) {
					request.getRequestDispatcher("/chargeStudy/update").forward(request);
					return;

				} else if (input == 2) {
					request.getRequestDispatcher("/chargeStudy/deleteRequest").forward(request);
					return;

				} else if (input == 0) {
					return;
				}

				// 타인이 작성한 글인 경우
			} else {
				int type = 0;
				System.out.println("1. 결제");

				//  관심목록이 있는 경우
				for (Member member : chargeStudy.getLikeMembers()) {
					if (member.getId().equals(AuthLogInHandler.getLoginUser().getId())) {
						type = 1;
						break;
					}
				}

				// 관심 목록이 있는 경우
				if (type == 1) {
					System.out.println("2. 관심목록 삭제");
					//          break;

					// 관심 목록이 없는 경우
				} else {
					System.out.println("2. 관심목록 추가");
					//          break;
				}
				System.out.println("0. 이전\n");
				System.out.println();

				int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");

				// 1. 결제
				if (input == 1) {
					request.getRequestDispatcher("/chargeStudy/payment").forward(request);
					return;

					// 2. 관심목록 추가 or 삭제
				} else if (input == 2) {
					// 관심 목록이 있는 경우
					if (type == 1) {
						request.getRequestDispatcher("/chargeStudy/interestDelete").forward(request);

						// 관심 목록이 없는 경우
					} else {
						request.getRequestDispatcher("/chargeStudy/interestAdd").forward(request);
					}

				} else if (input == 0) {
					return;

					//      if (chargeStudy.getWriter() == loginUser) {}
					//      else if (chargeStudy.getWriter() != loginUser) {}

				} else {
					System.out.println("존재하지 않는 메뉴 번호입니다.");
					continue;
				}
			}
		}
	}
}
