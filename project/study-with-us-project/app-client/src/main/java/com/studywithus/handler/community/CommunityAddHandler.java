package com.studywithus.handler.community;

import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class CommunityAddHandler implements Command {

  CommunityDao communityDao;

  public CommunityAddHandler(CommunityDao communityDao) {
    this.communityDao = communityDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[커뮤니티 / 생성] \n");

    Community community = new Community();

    System.out.println("1. 정보");
    System.out.println("2. 질문");
    System.out.println("3. 스몰톡");
    community.setCategory(Prompt.inputInt("카테고리를 선택하세요. > "));
    community.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    community.setContent(Prompt.inputString("내용을 입력하세요. > "));
    community.setWriter(AuthLogInHandler.getLoginUser());

    communityDao.insert(community);

    System.out.println();
    System.out.println("커뮤니티 등록이 완료되었습니다.");
  }
}
