package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;

public abstract class AbstractCommunityHandler implements Command {

  protected List<Community> communityList;


  public AbstractCommunityHandler(List<Community> communityList) {
    this.communityList = communityList;
  }

  // 정보 게시글 번호 조회
  protected Community findInfoByNo(int no) {
    for (Community communityInfo : communityList) {
      if (communityInfo.getNo() == no) {
        return communityInfo;

      }
    }
    return null;
  }

}
