package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;

public abstract class AbstractCommunityHandler implements Command {

  public List<Community> communityList;

  public AbstractCommunityHandler(List<Community> communityList) {
    this.communityList = communityList;;
  }

  //정보 게시글 번호 조회
  protected Community findByNo(int no) {
    for (Community community : communityList) {
      if (community.getNo() == no) {
        return community;
      }
    }
    return null;
  }

}
