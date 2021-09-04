package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.CommunityInfo;
import com.studywithus.domain.CommunityQa;
import com.studywithus.domain.CommunityTalk;

public abstract class AbstractCommunityHandler implements Command {

  protected List<CommunityInfo> communityInfoList;
  protected List<CommunityQa> communityQaList;
  protected List<CommunityTalk> communityTalkList;

  public AbstractCommunityHandler(List<CommunityInfo> communityInfoList,List<CommunityQa> communityQaList,List<CommunityTalk> communityTalkList) {
    this.communityInfoList = communityInfoList;
    this.communityQaList = communityQaList;
    this.communityTalkList = communityTalkList;
  }

  //정보 게시글 번호 조회
  protected CommunityInfo findInfoByNo(int no) {
    for (CommunityInfo communityInfo : communityInfoList) {
      if (communityInfo.getNo() == no) {
        return communityInfo;
      }
    }
    return null;
  }

  //질문 게시글 번호 조회
  protected CommunityQa findQaByNo(int no) {
    for (CommunityQa communityQa : communityQaList) {
      if (communityQa.getNo() == no) {
        return communityQa;
      }
    }
    return null;
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
