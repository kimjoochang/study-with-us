package com.studywithus.handler.community;

import java.sql.Date;
import java.util.List;

import com.studywithus.domain.Community;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class NoticeAddHandler extends AbstractCommunityHandler {

	public NoticeAddHandler(List<Community> communityList) {
		super(communityList);
		// ? 공지 리스트 커뮤니티 리스트 분리해야함??

	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[공지 / 생성] \n");

		Community community = new Community();

		// 공지 생성 시 게시글 번호 자동 부여
		if (!communityList.isEmpty()) {
			Community lastElement = communityList.get(communityList.size() - 1);
			community.setNo(lastElement.getNo() + 1);
		} else {
			community.setNo(1);
		}

		community.setTitle(Prompt.inputString("제목을 입력하세요. > "));
		community.setContent(Prompt.inputString("내용을 입력하세요. > "));
		community.setWriter(AuthLogInHandler.getLoginUser());
		community.setRegisteredDate(new Date(System.currentTimeMillis()));

		communityList.add(community);

		System.out.println();
		System.out.println("공지 등록이 완료되었습니다.\n");
	}
}
