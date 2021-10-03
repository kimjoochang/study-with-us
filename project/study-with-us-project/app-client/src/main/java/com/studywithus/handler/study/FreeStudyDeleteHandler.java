package com.studywithus.handler.study;

import java.util.HashMap;

import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class FreeStudyDeleteHandler implements Command {

	RequestAgent requestAgent;

	public FreeStudyDeleteHandler(RequestAgent requestAgent) {
		this.requestAgent = requestAgent;
	}

	@Override
	public void execute(CommandRequest request) throws Exception{
		System.out.println("[무료 스터디 / 삭제]");
		int no = (int) request.getAttribute("freeNo");

		HashMap<String,String> params = new HashMap<>();
		params.put("no", String.valueOf(no));

		requestAgent.request("freeStudy.selectOne", params);


		if (requestAgent.getStatus().equals(requestAgent.FAIL)) {
			System.out.println("해당 번호의 게시글이 없습니다.");
			return;
		}

		// 조건문에 관리자도 해당
		if (freeStudy.getWriter().getEmail() != AuthLogInHandler.getLoginUser().getEmail()) {
			System.out.println("삭제 권한이 없습니다.");
			return;
		}

		String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
		System.out.println();
		while (true) {
			if (input.equalsIgnoreCase("n") || input.length() == 0) {
				System.out.println("게시글 삭제를 취소하였습니다.");
				return;

			} else if (!input.equalsIgnoreCase("y")) {
				System.out.println("다시 입력하세요.\n");
				continue;

			} else {
				break;
			}
		}

		requestAgent.request("freeStudy.delete", params);

		System.out.println("게시글을 삭제하였습니다.");
	}
}
