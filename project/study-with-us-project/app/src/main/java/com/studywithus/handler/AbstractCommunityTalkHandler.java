package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.CommunityTalk;

public abstract class AbstractCommunityTalkHandler implements Command {

  protected List<CommunityTalk> communityTalkList;

  public AbstractCommunityTalkHandler(List<CommunityTalk> communityTalkList) {
    this.communityTalkList = communityTalkList;
  }

  //스몰톡 게시글 번호 조회
  protected CommunityTalk findTalkByNo(int no) {
    for (CommunityTalk communityTalk : communityTalkList) {
      if (communityTalk.getNo() == no) {
        return communityTalk;
      }
    }
    return null;
  }

}
