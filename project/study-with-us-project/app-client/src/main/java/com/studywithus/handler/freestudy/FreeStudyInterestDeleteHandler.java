package com.studywithus.handler.study;

import java.util.HashMap;

import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class FreeStudyInterestDeleteHandler implements Command {

	RequestAgent requestAgent;

	public FreeStudyInterestDeleteHandler(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
		// super(freeStudyList);
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[무료 스터디 / 상세보기 / 관심 목록 / 삭제]\n");
		int type = 0; // 일치하는 값 X -> 게시글 없다는 출력문 한 번만 출력
		// int no = Prompt.inputInt("삭제할 관심 목록 번호를 입력하세요. > ");
		int no = (int) request.getAttribute("freeNo");

		// Study freeInterest = findByNo(no);

		HashMap<String, String> params = new HashMap<>();
		params.put("no", String.valueOf(no));

		requestAgent.request("freeStudy.selectOne", params);

		if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			System.out.println("무료 스터디 상세보기 실패!");
			System.out.println(requestAgent.getObject(Study.class));
			return;
		}

		Study freeStudy = requestAgent.getObject(Study.class);

		// if (request.getAttribute("freeNo") == null) {
		for (Member likeMember : freeStudy.getLikeMembers()) {
			if (likeMember.getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
				type = 1;
				break;
			}
		}

		if (type == 0) {
			System.out.println("해당 번호의 관심 목록이 없습니다.");
			return;
		}

		while (true) {
			String input = Prompt.inputString("무료 스터디 관심 목록을 삭제하시겠습니까? (y/N) ");

			if (input.equalsIgnoreCase("n") || input.length() == 0) {
				System.out.println("무료 스터디 관심 목록 삭제를 취소하였습니다.\n");
				return;

			} else if (input.equalsIgnoreCase("y")) {
				freeStudy.getLikeMembers().remove(AuthLogInHandler.getLoginUser());
				freeStudy.setLikeMembers(freeStudy.getApplicants());
				requestAgent.request("freeStudy.update", freeStudy);

				if (requestAgent.getStatus().equals(RequestAgent.SUCCESS)) {
					System.out.println("무료 스터디 관심 목록 삭제 성공!");
					return;

				} else {
					System.out.println("무료 스터디 관심 목록 삭제 실패!");
					return;
				}

			} else {
				System.out.println("다시 입력하세요.\n");
				continue;
			}
		}

		// List<Member> likeUser = freeStudyInterest.getLikeMembers();
		// likeUser.remove(AuthLogInHandler.getLoginUser());
		// freeStudyInterest.setLikeMembers(likeUser);
		//
		// requestAgent.request("freeStudy.interest.delete", freeStudyInterest);
		//
		// System.out.println();
		// System.out.println("무료 스터디 관심 목록을 삭제하였습니다.");

		// } else {
		// int no = (int) request.getAttribute("freeNo");

		// Study freeInterest = findByNo(no);

		// if (freeStudy == null) {
		// System.out.println("해당 번호의 게시글이 없습니다.");
		// return;
		// }
		//
		// if (freeStudy.getLikeMembers().isEmpty()) {
		// System.out.println("무료 스터디 관심목록이 존재하지 않습니다.\n");
		// return;
		// }
		//
		// while (true) {
		// String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
		//
		// if (input.equalsIgnoreCase("n") || input.length() == 0) {
		// System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
		// return;
		//
		// } else if (input.equalsIgnoreCase("y")) {

		// } else {
		// System.out.println("다시 입력하세요.\n");
		// continue;
		// }
		// }
		// }

		// List<Member> likeMember = freeStudyInterest.getLikeMembers();
		// likeMember.remove(AuthLogInHandler.getLoginUser());
		// freeStudyInterest.setLikeMembers(likeMember);

		// System.out.println();
		// System.out.println("무료 스터디 관심 목록을 삭제하였습니다.");
	}
}
