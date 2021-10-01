package com.studywithus.handler.community;

import java.sql.Date;
import java.util.List;

import com.studywithus.domain.Community;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class MyCommentAddHandler extends AbstractCommunityHandler {

	public MyCommentAddHandler(List<Community> communityList) {
		super(communityList);

	}
	// 나의 댓글 리스트? 커뮤니티 리스트 사용 or 분리?

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[나의 댓글 / 생성] \n");

		Community community = new Community();

		// 나의 댓글 생성 시에도 번호가 존재해야하는지?
		// if (!communityList.isEmpty()) {
		// Community lastElement = communityList.get(communityList.size() - 1);
		// community.setNo(lastElement.getNo() + 1);
		// } else {
		// community.setNo(1);
		// }

		// 나 (글쓴이)의 정보
		community.setWriter(AuthLogInHandler.getLoginUser());

		// 내가 쓴 댓글 내용
		community.setContent(Prompt.inputString("내용을 입력하세요. > "));
		community.setRegisteredDate(new Date(System.currentTimeMillis()));

		communityList.add(community);

		System.out.println();
		System.out.println("게시글 등록이 완료되었습니다.\n");
	}
}
