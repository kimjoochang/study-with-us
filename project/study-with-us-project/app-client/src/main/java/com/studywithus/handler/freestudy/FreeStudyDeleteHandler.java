package com.studywithus.handler.freestudy;

import com.studywithus.dao.FreeStudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyDeleteHandler implements Command {

	FreeStudyDao freeStudyDao;

	public FreeStudyDeleteHandler(FreeStudyDao freeStudyDao) {
		this.freeStudyDao = freeStudyDao;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[무료 스터디 / 삭제]");
		int no = (int) request.getAttribute("freeNo"); // 에러

		Study freeStudy = freeStudyDao.findByNo(no);

		if (freeStudy == null) {
			System.out.println("해당 번호의 무료 스터디가 없습니다.");
			return;
		}

		// Study freeStudy = requestAgent.getObject(Study.class);

		// [수정] 조건문에 관리자도 해당
		// if (freeStudy.getWriter().getEmail() != AuthLogInHandler.getLoginUser().getEmail()
		// || AuthLogInHandler.getLoginUser().getEmail().equals("root@test.com")) {
		// System.out.println("삭제 권한이 없습니다.");
		// return;
		// }

		if (freeStudy.getWriter().getNo() != AuthLogInHandler.getLoginUser().getNo() || AuthLogInHandler.getLoginUser().getEmail().equals("root@test.com")) {
			System.out.println("삭제 권한이 없습니다.");
			return;
		}

		while (true) {
			String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
			System.out.println();

			if (input.equalsIgnoreCase("n") || input.length() == 0) {
				System.out.println("무료 스터디 삭제를 취소하였습니다.");
				return;

			} else if (!input.equalsIgnoreCase("y")) {
				System.out.println("다시 입력하세요.\n");
				continue;

			} else {
				break;
			}
		}

		freeStudyDao.delete(no);

		System.out.println("무료 스터디를 삭제하였습니다.");
	}
}
