package com.studywithus.handler.comment;

import com.studywithus.dao.CommentDao;
import com.studywithus.domain.Comment;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class CommentDeleteHandler implements Command {

  CommentDao commentDao;

  public CommentAddHandler(CommentDao commentDao) {
    this.commentDao = commentDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[커뮤니티 / 댓글 / 작성]\n");

    int no = (int) request.getAttribute("communityNo");

    Comment comment = new Comment();

    comment.setCommunityNo(no);
    comment.setContent(Prompt.inputString("내용을 입력하세요. > "));
    comment.setEmail(AuthLogInHandler.getLoginUser().getEmail());

    commentDao.insert(comment);

    System.out.println();
    System.out.println("댓글 등록이 완료되었습니다.\n");
  }

}
