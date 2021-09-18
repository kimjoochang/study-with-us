package com.studywithus.handler;

import java.util.List;

import com.studywithus.domain.Community;
import com.studywithus.domain.Member;

public class MyPostListHandlerSY extends AbstractCommunityHandler{

	List<Community> myPostList;

	public MyPostListHandlerSY(List<Community> myPostList) {
		super(myPostList);
	}

	@Override
	public void execute(CommandRequest request) {
		System.out.println("[마이 페이지 / 나의 활동 / 내 게시글 / 내 게시글 조회]");

		if (communityList != null) {

			for (Community community : communityList) {
				System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
						community.getNo(),
						community.getTitle(), 
						community.getWriter().getName(),
						community.getRegisteredDate(), 
						community.getViewCount(),
						community.getLike());
				System.out.println();
				return;
			} 
		} 
		System.out.println();
		System.out.println("내 게시글이 존재하지 않습니다.\n");
	}

	public void execute(Command request) {
		System.out.println("[마이 페이지 / 나의 활동 / 내 게시글 / 내 게시글 조회]");

		String id = AuthLoginHandler.getLoginUser().getId();
		Community cmntID= findById(id);

		if (cmntID != null) {
			for (Community community : communityList) {

				System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
						community.getNo(),
						community.getTitle(), 
						community.getWriter().getName(),
						community.getRegisteredDate(), 
						community.getViewCount(),
						community.getLike());
				System.out.println();
			} else {
				System.out.println();
				System.out.println("s");
				return;
			}
		}
	}


	// ID로 회원별 게시글 조회
	protected Member findById(String Id) {
		for (Community writer : myPostList) {
			if (member.getId() == Id) {
				return member;
			} else {

			}
		}
		return null;
	}

}
