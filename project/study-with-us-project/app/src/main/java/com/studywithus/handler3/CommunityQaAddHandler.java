package com.studywithus.handler3;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.util.Prompt;

public class CommunityQaAddHandler extends AbstractCommunityHandler{

  public CommunityQaAddHandler(List<Community> communityList) {
    super(communityList);
  }

  // 질문 게시글 생성
  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 질문 / 생성] \n");

    Community community = new Community();

    community.setNo(Prompt.inputInt("번호? "));
    community.setTitle(Prompt.inputString("제목? "));
    community.setContent(Prompt.inputString("내용? "));
    community.setWriter(Prompt.inputString("작성자? "));
    community.setRegisteredDate(new Date(System.currentTimeMillis()));

    communityList.add(community);

    System.out.println();
    System.out.println("질문 게시글 등록이 완료되었습니다.\n");
  }

}