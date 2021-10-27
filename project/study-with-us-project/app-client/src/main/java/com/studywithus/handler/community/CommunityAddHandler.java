package com.studywithus.handler.community;

import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class CommunityAddHandler implements Command {

  CommunityDao communityDao;
  SqlSession sqlSession;

  public CommunityAddHandler(CommunityDao communityDao, SqlSession sqlSession) {
    this.communityDao = communityDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[커뮤니티 / 생성] \n");

    // 클라이언트한테 요청받을 때 쓸 변수
    int categoryNo = 0;

    Community community = new Community();

    community.setCategory(categoryNo);
    community.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    community.setContent(Prompt.inputString("내용을 입력하세요. > "));
    community.setWriter(AuthLogInHandler.getLoginUser());

    communityDao.insert(community);

    sqlSession.commit();

    System.out.println();
    System.out.println("커뮤니티 등록이 완료되었습니다.");
  }
}
