package com.studywithus.handler.community;

import java.util.Collection;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;

public class CommunityListHandler implements Command {

  RequestAgent requestAgent;

  public CommunityListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[커뮤니티 / 조회]\n");
    System.out.println();

    requestAgent.request("community.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("커뮤니티 게시글이 존재하지 않습니다.\n");
      return;
    }

    Collection<Community> communityList = requestAgent.getObjects(Community.class);

    for (Community community : communityList) {
      System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
          community.getNo(), community.getTitle(), community.getWriter().getEmail(),
          community.getRegisteredDate(), community.getViewCount(), community.getLike());
    }
    System.out.println();
  }
}
