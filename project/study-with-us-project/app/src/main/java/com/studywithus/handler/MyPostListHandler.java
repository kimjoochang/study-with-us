package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;

public class MyPostListHandler implements Command {

  List<Community> myPostList;

  public MyPostListHandler(List<Community> myPostList) {
    this.myPostList = myPostList;
  }

  @Override
  public void execute() {
    System.out.println("[마이 페이지 / 나의 활동 / 내 게시글 / 내 게시글 조회]");

    if (myPostList != null) {

      for (Community community : myPostList.get(AuthLoginHandler.getLoginUser().getId())) {
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
}
