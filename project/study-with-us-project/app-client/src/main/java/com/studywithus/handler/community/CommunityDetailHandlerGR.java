package com.studywithus.handler.community;

import java.util.HashMap;

import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
//import com.studywithus.domain.Member;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
//import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class CommunityDetailHandlerGR implements Command{

	RequestAgent requestAgent;
	String updateKey;
	String deleteKey;

	public CommunityDetailHandlerGR(RequestAgent requestAgent, String updateKey, String deleteKey) {
		this.requestAgent = requestAgent;
		this.updateKey = updateKey;
		this.deleteKey = deleteKey;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {
		System.out.println("[커뮤니티 / 상세보기] \n");
		int no = Prompt.inputInt("번호를 입력하세요. > ");

		HashMap<String,String> params = new HashMap<>();
		params.put("no", String.valueOf(no));

		requestAgent.request("community.selectOne", params);

		if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
			System.out.println();
			System.out.println("해당 번호의 커뮤니티가 없습니다.\n");
			return;
		}

		Community community = requestAgent.getObject(Community.class);

		System.out.println("");
		System.out.printf("제목: %s\n", community.getTitle());
		System.out.printf("내용: %s\n", community.getContent());
		System.out.printf("작성자: %s\n", community.getWriter().getName());
		System.out.printf("등록일: %s\n", community.getRegisteredDate());

		community.setViewCount(community.getViewCount() + 1);
		System.out.printf("조회수: %d\n", community.getViewCount());
		System.out.println();

		request.setAttribute("communityNo", no);

		// 내가 쓴 글인 경우
		if (community.getWriter().getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
			while (true) {
				System.out.println("1. 수정");
				System.out.println("2. 삭제");
				System.out.println("0. 이전");
				System.out.println();

				int num = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
				System.out.println();

				if (num == 1) {
					request.getRequestDispatcher(updateKey).forward(request);

				} else if (num == 2) {
					request.getRequestDispatcher(deleteKey).forward(request);

				} else if (num == 0) {
					return;

				} else {
					System.out.println("다시 입력하세요.\n");
					continue;
				}
				return;
			}
		}
	}
}
