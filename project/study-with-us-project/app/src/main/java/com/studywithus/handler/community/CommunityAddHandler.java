package com.studywithus.handler.community;

import java.sql.Date;
import java.util.List;

import com.studywithus.domain.Community;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class CommunityAddHandler extends AbstractCommunityHandler {

	public CommunityAddHandler(List<Community> communityList) {
		super(communityList);

	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[커뮤니티 / 생성] \n");

		Community community = new Community();

		// 커뮤니티 생성 시 게시글 번호 자동 부여
		if (!communityList.isEmpty()) {
			Community lastElement = communityList.get(communityList.size() - 1);
			community.setNo(lastElement.getNo() + 1);
		} else {
			community.setNo(1);
		}

		community.setNo(Prompt.inputInt("번호를 입력하세요. > "));
		community.setTitle(Prompt.inputString("제목을 입력하세요. > "));
		community.setContent(Prompt.inputString("내용을 입력하세요. > "));
		community.setWriter(AuthLogInHandler.getLoginUser());
		community.setRegisteredDate(new Date(System.currentTimeMillis()));

		communityList.add(community);

		System.out.println();
		System.out.println("게시글 등록이 완료되었습니다.\n");
	}
}
