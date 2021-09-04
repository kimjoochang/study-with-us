package com.studywithus.handler;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.CommunityInfo;
import com.studywithus.util.Prompt;

public class CommunityInfoAddHandler extends AbstractCommunityInfoHandler{

  public CommunityInfoAddHandler(List<CommunityInfo> communityInfoList) {
    super(communityInfoList);
  }

  // 정보 게시글 생성
  @Override
  public void execute() {
    // System.out.println("[커뮤니티 / 정보 / 생성] \n");

    CommunityInfo communityInfo = new CommunityInfo();

    communityInfo.setNo(Prompt.inputInt("번호? "));
    communityInfo.setTitle(Prompt.inputString("제목? "));
    communityInfo.setContent(Prompt.inputString("내용? "));
    communityInfo.setWriter(Prompt.inputString("작성자? "));
    communityInfo.setRegisteredDate(new Date(System.currentTimeMillis()));

    communityInfoList.add(communityInfo);

    System.out.println();
    System.out.println("정보 게시글 등록이 완료되었습니다.\n");
  }

}
