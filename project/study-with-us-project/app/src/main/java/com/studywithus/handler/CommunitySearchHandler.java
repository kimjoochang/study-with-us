package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.util.Prompt;

public class CommunitySearchHandler extends AbstractCommunityHandler {

  Community community = new Community();

  public CommunitySearchHandler(List<Community> communityList) {
    super(communityList);
  }

  @Override
  public void execute() {
    System.out.println("[게시글 검색]");

    String input = Prompt.inputString("검색어? ");

    for (Community community : communityList) {
      if (!community.getTitle().contains(input) &&
          !community.getContent().contains(input) &&
          !community.getWriter().getName().contains(input)) {
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







