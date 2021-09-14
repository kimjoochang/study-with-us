package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;

public abstract class AbstractCommunityHandler implements Command {

  protected List<Community> communityList;
  protected List<Member> myPostList;

  public AbstractCommunityHandler(List<Community> communityList, List<Community> myPostList) {
    this.communityList = communityList;
  }

  // 커뮤니티 게시글 번호 조회
  protected Community findByNo(int no) {
    for (Community community : communityList) {
      if (community.getNo() == no) {
        return community;
      }
    }
    return null;
  }
}
