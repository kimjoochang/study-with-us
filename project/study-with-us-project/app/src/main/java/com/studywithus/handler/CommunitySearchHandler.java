package com.studywithus.handler;

import com.studywithus.domain.Board;
import com.studywithus.util.Prompt;

public class CommunitySearchHandler extends AbstractBoardHandler {

  //  Community community = new Community();
  //
  //  public CommunitySearchHandler(List<Community> communityList) {
  //    super(communityList);
  //  }

  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 검색]");

    String input = Prompt.inputString("검색어? ");
    System.out.println();

    for (Board board : boardList) {
      if (!board.getTitle().contains(input) &&
          !board.getContent().contains(input) &&
          !board.getWriter().getName().contains(input)) {
        System.out.println("입력하신 검색어가 포함된 게시물이 없습니다.");
        continue;
      }
      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
          board.getNo(), 
          board.getTitle(), 
          board.getWriter().getName(),
          board.getRegisteredDate(),
          board.getViewCount(), 
          board.getLike());
    }
  }
}







