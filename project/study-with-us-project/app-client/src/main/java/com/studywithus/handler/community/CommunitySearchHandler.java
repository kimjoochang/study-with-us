package com.studywithus.handler.community;

import java.util.Collection;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class CommunitySearchHandler implements Command {

  CommunityDao communityDao;

  public CommunitySearchHandler(CommunityDao communityDao) {
    this.communityDao = communityDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[커뮤니티 / 검색]\n");

    String input = Prompt.inputString("검색할 키워드를 입력하세요. > ");
    System.out.println();

    int categoryNo = 0;

    Collection<Community> communityList = communityDao.findByKeyword(input, categoryNo);

    if (communityList == null) {
      System.out.println("입력하신 키워드가 포함된 게시글이 없습니다.\n");
      return;
    }

    for (Community community : communityList) {
      System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
          community.getNo(), community.getTitle(), community.getWriter().getEmail(),
          community.getRegisteredDate(), community.getViewCount(), community.getLike());
      System.out.println();
    }

  }
}
