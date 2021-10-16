package com.studywithus.handler.comment;

import com.studywithus.dao.CommentDao;
import com.studywithus.domain.Comment;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class CommentDeleteHandler implements Command {

  CommentDao commentDao;

  public CommentDeleteHandler(CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[커뮤니티 / 댓글 삭제]\n");

    int no = (int) request.getAttribute("commentNo");

    Comment comment = commentDao.findByNo(no);

    if (comment == null) {
      System.out.println("해당 번호의 댓글이 없습니다.");
      return;
    }

    if (comment.getWriter().getEmail() != AuthLogInHandler.getLoginUser().getEmail()) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }
    else if (comment.getWriter().getEmail() == AuthLogInHandler.getLoginUser().getEmail()
        || AuthLogInHandler.getLoginUser().getEmail().equals("root@test.com"))
      while (true) {
        String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

        if (input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("게시글 삭제를 취소하였습니다.");
          return;

        } else if (input.equalsIgnoreCase("y")) {
          commentDao.delete(no);
          System.out.println("게시글을 삭제하였습니다.");
          return;

        } else {
          System.out.println("다시 입력하세요.\n");
          continue;
        }
      }
  }








}
