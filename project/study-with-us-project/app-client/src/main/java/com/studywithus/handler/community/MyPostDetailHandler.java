package com.studywithus.handler.community;

import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class MyPostDetailHandler implements Command {

  CommunityDao communityDao;

  public MyPostDetailHandler(CommunityDao communityDao) {
    this.communityDao = communityDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 나의 활동 / 나의 게시글 / 상세보기]");
    int no = Prompt.inputInt("나의 게시글 번호를 입력하세요. > ");

    Community community = communityDao.findByNo(no);

    if (community == null) {
      System.out.println("커뮤니티 게시글이 존재하지 않습니다.");
      return;
    }

    int count = 0;

    // 로그인한 회원의 정보와 커뮤니티 게시글의 작성자가 일치한다면,
    if (community.getWriter().getNo() == AuthLogInHandler.getLoginUser().getNo()
        && no == community.getNo()) {
      count++;

      System.out.println();
      community.setViewCount(community.getViewCount() + 1);
      System.out.printf("번호: %d\n", community.getNo());
      System.out.printf("제목: %s\n", community.getTitle());
      System.out.printf("내용: %s\n", community.getContent());
      System.out.printf("작성자: %s\n", community.getWriter().getName());
      System.out.printf("등록일: %s\n", community.getRegisteredDate());
      System.out.printf("조회수: %d\n", community.getViewCount());
      System.out.println();
    }

    if (count == 0) {
      System.out.println("나의 게시글이 존재하지 않습니다.\n");
      return;
    }

    Member loginUser = AuthLogInHandler.getLoginUser();
    if (loginUser == null || (community.getWriter().getNo() != loginUser.getNo()
        && !loginUser.getEmail().equals("root@test.com"))) {
      return;
    }

    request.setAttribute("communityNo", no);

    // 내가 쓴 글인 경우
    if (community.getWriter().getNo() == AuthLogInHandler.getLoginUser().getNo()) {
      while (true) {
        System.out.println("1. 수정");
        System.out.println("2. 삭제");
        System.out.println("0. 이전");
        System.out.println();

        int num = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
        System.out.println();

        if (num == 1) {
          request.getRequestDispatcher("/community/update").forward(request);

        } else if (num == 2) {
          request.getRequestDispatcher("/community/delete").forward(request);

        } else if (num == 0) {
          return;

        } else {
          System.out.println("다시 입력하세요.\n");
          continue;
        }
        return;
      }
    }
  }
}

/*
 * 상세보기에서 수정/삭제로 이동 Member loginUser = AuthLogInHandler.getLoginUser(); if (loginUser == null ||
 * myPost.getWriter().getId() != loginUser.getId()) { return; }
 * 
 * request.setAttribute("communityNo", no);
 * 
 * while (true) {
 * 
 * System.out.println("1. 수정"); System.out.println("2. 삭제"); System.out.println("0. 이전");
 * System.out.println(); int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
 * 
 * switch (input) { case 1: request.getRequestDispatcher(updateKey).forward(request); return; case
 * 2: request.getRequestDispatcher(deleteKey).forward(request); return; case 0: return; default:
 * System.out.println(""); System.out.println("무효한 메뉴 번호입니다.\n"); } }
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
