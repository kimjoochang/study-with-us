package com.studywithus.handler.community;

import java.util.List;

import com.studywithus.domain.Community;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class NoticeUpdateHandler extends AbstractCommunityHandler{

	public NoticeUpdateHandler(List<Community> communityList) {
		super(communityList);
	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[공지 / 수정] \n");

		int no = (int) request.getAttribute("communityNo");

		Community community = findByNo(no);

		if (community == null) {
			System.out.println();
			System.out.println("해당 번호의 공지가 없습니다.\n");
			return;
		}

		if (community.getWriter() != AuthLogInHandler.getLoginUser()) {
			System.out.println("변경 권한이 없습니다.\n");
			return;
		}

		String title = Prompt.inputString(String.format("[%s] 수정할 제목을 입력하세요. > ", community.getTitle()));
		String content = Prompt.inputString(String.format("[%s] 수정할 내용을 입력하세요. > ", community.getContent()));
		System.out.println();

		while (true) {
			String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");

			if (input.equalsIgnoreCase("n") || input.length() == 0) {
				System.out.println("공지 수정을 취소하였습니다.\n");
				return;

			} else if (input.equalsIgnoreCase("y")){

				community.setTitle(title);
				community.setContent(content);

				System.out.println("");
				System.out.println("공지를 수정하였습니다.\n");
				return;

			} else {
				System.out.println("올바른 값을 입력하세요.\n");
				continue;
			}
		}
	}
}
