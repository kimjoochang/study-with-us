package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;

public class CommunityListHandler extends AbstractCommunityHandler{

  public CommunityListHandler(List<Community> communityList) {
    super(communityList);
  }

  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 조회]\n");

    //    Community community;
    //    if (community == null) {
    //      System.out.println();
    //      System.out.println("등록된 게시글이 없습니다.\n");
    //      return;
    //    }

    for (Community community : communityList) {

      if (community == null) {
        System.out.println();
        System.out.println("등록된 게시글이 없습니다.\n");
        return;
      }

      System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
          community.getNo(),
          community.getTitle(), 
          community.getWriter().getName(),
          community.getRegisteredDate(), 
          community.getViewCount(),
          community.getLike());

      System.out.println();
    }
  }
}
