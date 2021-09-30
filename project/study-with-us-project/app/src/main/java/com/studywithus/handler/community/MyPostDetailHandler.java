package com.studywithus.handler.community;

import java.util.ArrayList;
import java.util.List;

import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class MyPostDetailHandler implements Command {

	List<Community> communityQaList;
	List<Community> communityInfoList;
	List<Community> communityTalkList;

	public MyPostDetailHandler(List<Community> communityQaList, List<Community> communityInfoList,
			List<Community> communityTalkList) {
		this.communityQaList = communityQaList;
		this.communityInfoList = communityInfoList;
		this.communityTalkList = communityTalkList;
		// this.updateKey = updateKey;
		// this.deleteKey = deleteKey;
	}

	@Override
	public void execute(CommandRequest request) throws Exception {

		System.out.println("[마이 페이지 / 나의 활동 / 나의 게시글 / 상세보기]\n");

		List<Community> myPostList = new ArrayList<>();

		myPostList.addAll(communityInfoList);
		myPostList.addAll(communityQaList);
		myPostList.addAll(communityTalkList);

		// int myPostNo = Prompt.inputInt("번호를 입력하세요. > ");
		// Community myPost = findByMyPostNo(myPostNo);

		if (myPostList.isEmpty() == true) {
			System.out.println("커뮤니티 게시글이 존재하지 않습니다.\n");
			return;
		}

		int count = 0;
		int myPostNo = Prompt.inputInt("나의 게시글 번호를 입력하세요. > ");

		for (Community myPost : myPostList) {

			// 로그인한 회원의 정보와 커뮤니티 게시글의 작성자가 일치한다면,
			if (myPost.getWriter().getId().equals(AuthLogInHandler.getLoginUser().getId())
					&& myPostNo == myPost.getMyPostNo()) {

				count++;

				System.out.println();
				System.out.printf("나의 게시글 번호:  %d\n", myPost.getMyPostNo());
				System.out.printf("커뮤니티 게시글 번호:  %d\n", myPost.getNo());
				System.out.printf("제목: %s\n", myPost.getTitle());
				System.out.printf("내용: %s\n", myPost.getContent());
				System.out.printf("작성자: %s\n", myPost.getWriter().getName());
				System.out.printf("등록일: %s\n", myPost.getRegisteredDate());

				myPost.setViewCount(myPost.getViewCount() + 1);
				System.out.printf("조회수: %d\n", myPost.getViewCount());

				System.out.println();

			}
		}

		if (count == 0) {
			System.out.println("나의 게시글이 존재하지 않습니다.\n");
		}
	}

	/*
	 * 상세보기에서 수정/삭제로 이동 Member loginUser = AuthLogInHandler.getLoginUser(); if
	 * (loginUser == null || myPost.getWriter().getId() != loginUser.getId()) {
	 * return; }
	 * 
	 * request.setAttribute("communityNo", no);
	 * 
	 * while (true) {
	 * 
	 * System.out.println("1. 수정"); System.out.println("2. 삭제");
	 * System.out.println("0. 이전"); System.out.println(); int input =
	 * Prompt.inputInt("메뉴 번호를 선택하세요. > ");
	 * 
	 * switch (input) { case 1:
	 * request.getRequestDispatcher(updateKey).forward(request); return; case 2:
	 * request.getRequestDispatcher(deleteKey).forward(request); return; case 0:
	 * return; default: System.out.println("");
	 * System.out.println("무효한 메뉴 번호입니다.\n"); } }
	 */

	// 나의 게시글 번호로 커뮤니티 게시글 조회하는 메서드
	// private Community findByMyPostNo(int myPostNo) {
	// for (Community myPost : myPostList) {
	// if (myPost.getMyPostNo() == no) {
	// return myPost;
	// }
	// }
	// return null;
	// }
}
