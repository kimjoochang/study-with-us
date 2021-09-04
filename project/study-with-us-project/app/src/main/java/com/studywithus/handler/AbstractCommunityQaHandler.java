package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.CommunityQa;

public abstract class AbstractCommunityQaHandler implements Command {

  protected List<CommunityQa> communityQaList;

  public AbstractCommunityQaHandler(List<CommunityQa> communityQaList) {
    this.communityQaList = communityQaList;
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

}
