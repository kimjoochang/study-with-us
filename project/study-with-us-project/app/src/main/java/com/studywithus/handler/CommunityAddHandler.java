package com.studywithus.handler;

import java.sql.Date;
import com.studywithus.domain.Board;
import com.studywithus.handler2.AuthLoginHandler;
import com.studywithus.util.Prompt;

public class CommunityAddHandler extends AbstractBoardHandler{

  //  public CommunityAddHandler(List<Community> communityList) {
  //    super(communityList);
  //  }

  // 게시글 생성
  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 생성] \n");

    Board board = new Board();

    board.setNo(Prompt.inputInt("번호? "));
    board.setTitle(Prompt.inputString("제목? "));
    board.setContent(Prompt.inputString("내용? "));
    board.setWriter(AuthLoginHandler.getLoginUser());
    board.setRegisteredDate(new Date(System.currentTimeMillis()));

    boardList.add(board);

    System.out.println();
    System.out.println("게시글 등록이 완료되었습니다.\n");
  }

}
