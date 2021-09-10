package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Board;
import com.studywithus.handler2.Command;

public abstract class AbstractBoardHandler implements Command {

  protected List<Board> boardList;

  public AbstractBoardHandler(List<Board> boardList) {
    this.boardList = boardList;
  }

  // 게시글 번호 조회
  protected Board findByNo(int no) {
    for (Board board : boardList) {
      if (board.getNo() == no) {
        return board;
      }
    }
    return null;
  }

}
