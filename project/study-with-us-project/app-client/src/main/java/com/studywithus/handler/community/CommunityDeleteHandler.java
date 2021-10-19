package com.studywithus.handler.community;

import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class CommunityDeleteHandler implements Command {

  CommunityDao communityDao;

  public CommunityDeleteHandler(CommunityDao communityDao) {
    this.communityDao = communityDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[커뮤니티 / 삭제]\n");
    int no = (int) request.getAttribute("communityNo");

    Community community = communityDao.findByNo(no);

    if (community == null) {
      System.out.println("해당 번호의 커뮤니티가 없습니다.");
      return;
    }

    if (community.getWriter().getNo() != AuthLogInHandler.getLoginUser().getNo()) {
      System.out.println("삭제 권한이 없습니다.");
      return;
    }

    while (true) {
      String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("게시글 삭제를 취소하였습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        // requestAgent.request("community.delete", params);
        communityDao.delete(no);
        System.out.println("게시글을 삭제하였습니다.");
        return;

      } else {
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
  }
}
