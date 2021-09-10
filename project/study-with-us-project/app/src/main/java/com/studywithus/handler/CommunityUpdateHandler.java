package com.studywithus.handler;

import com.studywithus.domain.Board;
import com.studywithus.util.Prompt;

public class CommunityUpdateHandler extends AbstractBoardHandler{

  //  public CommunityUpdateHandler(List<Community> communityList) {
  //    super(communityList);
  //  }

  // 게시글 수정
  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 수정] \n");

    int no = Prompt.inputInt("번호? ");

    Board board = findByNo(no);

    if (board == null) {
      System.out.println();
      System.out.println("해당 번호의 게시글이 없습니다.\n");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", board.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", board.getContent()));

    String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("게시글 수정을 취소하였습니다.\n");
      return;
    }

    board.setTitle(title);
    board.setContent(content);

    System.out.println();
    System.out.println("게시글을 수정하였습니다.\n");
  }

}
