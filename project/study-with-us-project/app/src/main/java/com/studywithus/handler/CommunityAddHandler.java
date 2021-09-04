package com.studywithus.handler;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.util.Prompt;

public class CommunityAddHandler extends AbstractCommunityHandler{

  public CommunityAddHandler(List<Community> communityList) {
    super(communityList);
  }

  // 정보 게시글 생성
  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 생성] \n");

    Community communityInfo = new Community();

    communityInfo.setNo(Prompt.inputInt("번호? "));
    communityInfo.setTitle(Prompt.inputString("제목? "));
    communityInfo.setContent(Prompt.inputString("내용? "));
    communityInfo.setWriter(Prompt.inputString("작성자? "));
    communityInfo.setRegisteredDate(new Date(System.currentTimeMillis()));

    communityList.add(communityInfo);

    System.out.println();
    System.out.println("게시글 등록이 완료되었습니다.\n");
  }

}
