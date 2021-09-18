package com.studywithus.handler.community;

import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class CommunitySearchHandler extends AbstractCommunityHandler {

  Community community = new Community();

  public CommunitySearchHandler(List<Community> communityList) {
    super(communityList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[커뮤니티 / 검색]");

    String input = Prompt.inputString("검색할 키워드를 입력하세요. > ");
    System.out.println();

    for (Community community : communityList) {
      if (!community.getTitle().contains(input) &&
          !community.getContent().contains(input) &&
          !community.getWriter().getName().contains(input)) {

        System.out.println("입력하신 키워드가 포함된 게시글이 없습니다.");
        continue;
      }

      System.out.printf("%d, %s, %s, %s, %d, %d\n", 
          community.getNo(), 
          community.getTitle(), 
          community.getWriter().getName(),
          community.getRegisteredDate(),
          community.getViewCount(), 
          community.getLike());
    }
  }
}







