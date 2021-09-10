package com.studywithus.handler;

import com.studywithus.domain.Board;

public class CommunityListHandler extends AbstractBoardHandler{

  //  public CommunityListHandler(List<Community> communityList) {
  //    super(communityList);
  //  }

  // 게시글 조회

  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 조회]\n");

    for (Board board : boardList) {
      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
          board.getNo(),
          board.getTitle(), 
          board.getWriter().getName(),
          board.getRegisteredDate(), 
          board.getViewCount(),
          board.getLike());
      System.out.println();
    }
  }

}
