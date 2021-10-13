package com.studywithus.handler.community;

import java.util.Collection;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class MyPostListHandler implements Command {

  CommunityDao communityDao;

  public MyPostListHandler(CommunityDao communityDao) {
    this.communityDao = communityDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 나의 활동 / 나의 게시글 / 조회]\n");

    Collection<Community> communityList = communityDao.findAll();

    // [추가] 커뮤니티 게시판 자체가 비어있을 시, 아무것도 출력되지 않아서 아래의 조건문 추가함
    if (communityList == null) {
      System.out.println("커뮤니티 게시글이 존재하지 않습니다.\n");
      return;
    }

    // line66 무한출력 방지용으로 임의의 변수 선언
    int count = 0;

    for (Community myPost : communityList) {
      // 로그인한 회원의 정보와 커뮤니티 게시글의 작성자가 일치한다면,
      if (myPost.getWriter().getNo() == AuthLogInHandler.getLoginUser().getNo()) {
        count++;

        // 내가 작성한 커뮤니티 게시글을 아래의 형식으로 출력함
        System.out.printf(
            "[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
            myPost.getNo(), myPost.getTitle(), myPost.getWriter().getName(),
            myPost.getRegisteredDate(), myPost.getViewCount(), myPost.getLike());

        count++;
      }
    }

    if (count == 0) {
      System.out.println("나의 게시글이 존재하지 않습니다.\n");
    }
  }
}
