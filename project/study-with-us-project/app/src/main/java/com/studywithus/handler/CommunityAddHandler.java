package com.studywithus.handler;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.util.Prompt;

public class CommunityAddHandler extends AbstractCommunityHandler{

  public CommunityAddHandler(List<Community> communityList) {
    super(communityList);
  }

  // 게시글 생성
  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 생성] \n");

    Community community = new Community();

    community.setNo(Prompt.inputInt("번호? "));
    community.setTitle(Prompt.inputString("제목? "));
    community.setContent(Prompt.inputString("내용? "));
    community.setWriter(AuthLoginHandler.getLoginUser());
    community.setRegisteredDate(new Date(System.currentTimeMillis()));

    communityList.add(community);

    System.out.println();
    System.out.println("게시글 등록이 완료되었습니다.\n");
  }

}
