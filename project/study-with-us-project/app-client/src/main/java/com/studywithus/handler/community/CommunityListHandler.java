package com.studywithus.handler.community;

import java.util.Collection;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class CommunityListHandler implements Command {

  CommunityDao communityDao;

  public CommunityListHandler(CommunityDao communityDao) {
    this.communityDao = communityDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[커뮤니티 / 조회]\n");

    // 클라이언트한테 요청받을 때 쓸 변수
    int categoryNo = 0;

    Collection<Community> communityList = communityDao.findAll(categoryNo);

    if (communityList == null) {
      System.out.println("커뮤니티 게시글이 존재하지 않습니다.\n");
      return;
    }

    for (Community community : communityList) {

      System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
          community.getNo(), community.getTitle(), community.getWriter().getEmail(),
          community.getRegisteredDate(), community.getViewCount(), community.getLike());
    }
  }
}