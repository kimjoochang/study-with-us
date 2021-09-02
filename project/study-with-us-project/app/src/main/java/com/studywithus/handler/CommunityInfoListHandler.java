package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;

public class CommunityInfoListHandler extends AbstractCommunityHandler{

  public CommunityInfoListHandler(List<Community> communityList) {
    super(communityList);
  }

  // 정보 게시글 조회

  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 정보 게시글 조회]");

    for (Community community : communityList) {
      System.out.printf("%d, %s, %s, %s, %d, %d\n", community.getNo(),
          community.getTitle(), community.getWriter(),
          community.getRegisteredDate(), community.getViewCount(),
          community.getLike());
    }
  }

}
