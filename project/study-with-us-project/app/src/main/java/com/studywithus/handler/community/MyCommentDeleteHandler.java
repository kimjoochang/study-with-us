package com.studywithus.handler.community;

import java.util.List;

import com.studywithus.domain.Community;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class MyCommentDeleteHandler extends AbstractCommunityHandler {

	public MyCommentDeleteHandler(List<Community> communityList) {
		super(communityList);
	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[나의 댓글 / 삭제] \n");

		int no = (int) request.getAttribute("communityNo");

		Community community = findByNo(no);

		if (community == null) {
			System.out.println();
			System.out.println("다시 입력하세요.\n");
			return;
		}

		while (true) {
			String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

			if (input.equalsIgnoreCase("n") || input.length() == 0) {
				System.out.println("나의 댓글 삭제를 취소하였습니다.\n");
				return;

			} else if (input.equalsIgnoreCase("y")) {

				communityList.remove(community);
				System.out.println("나의 댓글을 삭제하였습니다.\n");
				return;

			} else {
				System.out.println("다시 입력하세요.\n");
				continue;
			}
		}
	}
}
