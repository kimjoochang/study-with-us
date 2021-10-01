package com.studywithus.handler.community;

import java.util.ArrayList;
import java.util.List;

import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class MyPostListHandler implements Command {

	List<Community> communityQaList;
	List<Community> communityInfoList;
	List<Community> communityTalkList;

	public MyPostListHandler(List<Community> communityQaList, List<Community> communityInfoList,
			List<Community> communityTalkList) {
		this.communityQaList = communityQaList;
		this.communityInfoList = communityInfoList;
		this.communityTalkList = communityTalkList;
	}

	@Override
	public void execute(CommandRequest request) {

		System.out.println("[마이 페이지 / 나의 활동 / 나의 게시글 / 조회]\n");

		List<Community> myPostList = new ArrayList<>();

		myPostList.addAll(communityInfoList);
		myPostList.addAll(communityQaList);
		myPostList.addAll(communityTalkList);

		// [추가] 커뮤니티 게시판 자체가 비어있을 시, 아무것도 출력되지 않아서 아래의 조건문 추가함
		if (myPostList.isEmpty() == true) {
			System.out.println("커뮤니티 게시글이 존재하지 않습니다.\n");
			return;
		}

		// line66 무한출력 방지용으로 임의의 변수 선언
		int count = 0;

		// 나의 게시글 일련번호 (나의 게시글 상세보기 시 prompt값으로 사용함)
		int myPostNo = 1;

		for (Community myPost : myPostList) {

			// 로그인한 회원의 정보와 커뮤니티 게시글의 작성자가 일치한다면,
			if (myPost.getWriter().getId().equals(AuthLogInHandler.getLoginUser().getId())) {

				count++;
				// 나의 게시글 번호 설정
				myPost.setMyPostNo(myPostNo);

				// 내가 작성한 커뮤니티 게시글을 아래의 형식으로 출력함
				System.out.printf(
						"[나의 게시글 번호 = %d, 커뮤니티 게시글 번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
						myPost.getMyPostNo(), myPost.getNo(), myPost.getTitle(), myPost.getWriter().getName(),
						myPost.getRegisteredDate(), myPost.getViewCount(), myPost.getLike());

				myPostNo++;
			}
		}

		if (count == 0) {
			System.out.println("나의 게시글이 존재하지 않습니다.\n");
		}
	}
}