package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;

public class CommunityListHandler extends AbstractCommunityHandler{

  public CommunityListHandler(List<Community> communityList) {
    super(communityList);
  }
  public CommunityListHandler(List<Community> communityQaList, int a) {
    super(communityQaList);
  }
  public CommunityListHandler(List<Community> communityTalkList, String b) {
    super(communityTalkList);
  }

  // 정보 게시글 조회

  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 정보 / 조회]\n");

    for (Community community : communityList) {
      System.out.printf("%d, %s, %s, %s, %d, %d\n", community.getNo(),
          community.getTitle(), community.getWriter(),
          community.getRegisteredDate(), community.getViewCount(),
          community.getLike());
      System.out.println();
    }
  }

}
