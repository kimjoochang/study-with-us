package com.studywithus.handler;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.domain.CommunityInfo;
import com.studywithus.util.Prompt;

public class CommunityAddHandler extends AbstractCommunityHandler {

  public CommunityAddHandler(List<CommunityInfo> communityInfoList) {
    super(communityInfoList);
  }

  public CommunityAddHandler(List<CommunityInfo> communityQaList) {
    super(communityQaList);
  }

  public CommunityAddHandler(List<CommunityInfo> communityTalkList) {
    super(communityTalkList);
  }

  // 정보 게시글 생성
  @Override
  public void execute() {
    // 뜯어낼 예정
    // System.out.println("[커뮤니티 / 정보 / 생성] \n");

    Community community = new Community();

    community.setNo(Prompt.inputInt("번호? "));
    community.setTitle(Prompt.inputString("제목? "));
    community.setContent(Prompt.inputString("내용? "));
    community.setWriter(Prompt.inputString("작성자? "));
    community.setRegisteredDate(new Date(System.currentTimeMillis()));

    communityList.add(community);

    // 뜯어낼 예정
    // System.out.println();
    // System.out.println("정보 게시글 등록이 완료되었습니다.\n");
  }

}
