package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;

public class CommunityQaListHandler extends AbstractCommunityHandler{

  public CommunityQaListHandler(List<Community> communityList) {
    super(communityList);
  }

  // 질문 게시글 조회

  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 질문 게시글 조회]");

    for (Community community : communityList) {
      System.out.printf("%d, %s, %s, %s, %d, %d\n", community.getNo(),
          community.getTitle(), community.getWriter(),
          community.getRegisteredDate(), community.getViewCount(),
          community.getLike());
    }
  }

}
