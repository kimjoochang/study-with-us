package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.CommunityInfo;

public abstract class AbstractCommunityInfoHandler implements Command {

  protected List<CommunityInfo> communityInfoList;

  public AbstractCommunityInfoHandler(List<CommunityInfo> communityInfoList) {
    this.communityInfoList = communityInfoList;
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

}
