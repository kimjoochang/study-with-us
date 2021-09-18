package com.studywithus.handler.community;

import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.handler.CommandRequest;

public class CommunityListHandler extends AbstractCommunityHandler{

  public CommunityListHandler(List<Community> communityList) {
    super(communityList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[커뮤니티 / 조회]\n");
    System.out.println();

    if (communityList.isEmpty() == true) {
      System.out.println("커뮤니티 게시글이 존재하지 않습니다.\n");
      return;
    }

    for (Community community : communityList) {
      System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
          community.getNo(),
          community.getTitle(), 
          community.getWriter().getId(),
          community.getRegisteredDate(), 
          community.getViewCount(),
          community.getLike());
    } 
    System.out.println();
  }
}
