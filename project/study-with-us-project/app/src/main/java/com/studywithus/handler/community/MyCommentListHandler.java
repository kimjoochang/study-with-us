package com.studywithus.handler.community;

import java.util.List;

import com.studywithus.domain.Community;
import com.studywithus.handler.CommandRequest;

public class MyCommentListHandler extends AbstractCommunityHandler {

	public MyCommentListHandler(List<Community> communityList) {
		super(communityList);
	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[나의 댓글 / 조회]\n");
		System.out.println();

		if (communityList.isEmpty() == true) {
			System.out.println("나의 댓글이 존재하지 않습니다.\n");
			return;
		}

		for (Community community : communityList) {
			System.out.printf("[번호 = %d, 작성자 = %s, 등록일 = %s, ]\n", community.getNo(),
					community.getWriter().getId(), community.getRegisteredDate());
		}
		System.out.println();
	}
}
