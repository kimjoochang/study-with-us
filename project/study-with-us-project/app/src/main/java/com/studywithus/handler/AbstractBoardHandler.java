package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Content;

public abstract class AbstractBoardHandler implements Command {

  protected List<Content> boardList;

  public AbstractBoardHandler(List<Content> boardList) {
    this.boardList = boardList;
  }

  // 게시글 번호 조회
  protected Content findByNo(int no) {
    for (Content board : boardList) {
      if (board.getNo() == no) {
        return board;
      }
    }
    return null;
  }

}
