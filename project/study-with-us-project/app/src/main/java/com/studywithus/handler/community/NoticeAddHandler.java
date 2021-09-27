package com.studywithus.handler.community;

import java.sql.Date;
import java.util.List;

import com.studywithus.domain.Community;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class NoticeAddHandler extends AbstractCommunityHandler{

	public NoticeAddHandler(List<Community> communityList) {
		super(communityList);

	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[공지 / 생성] \n");

		Community community = new Community();

		community.setNo(Prompt.inputInt("번호를 입력하세요. > "));
		community.setTitle(Prompt.inputString("제목을 입력하세요. > "));
		community.setContent(Prompt.inputString("내용을 입력하세요. > "));
		community.setWriter(AuthLogInHandler.getLoginUser());
		community.setRegisteredDate(new Date(System.currentTimeMillis()));

		communityList.add(community);

		System.out.println();
		System.out.println("공지 등록이 완료되었습니다.\n");
	}
}
