package com.studywithus.handler.community;

import java.util.Collection;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class MyPostListHandler implements Command {

  CommunityDao communityDao;

  public MyPostListHandler(CommunityDao communityDao) {
    this.communityDao = communityDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이 페이지 / 나의 활동 / 나의 게시글 / 조회]\n");

    Collection<Community> communityList = communityDao.findAll();

    // [추가] 커뮤니티 게시판 자체가 비어있을 시, 아무것도 출력되지 않아서 아래의 조건문 추가함
    if (communityList == null) {
      System.out.println("커뮤니티 게시글이 존재하지 않습니다.");
      return;
    }

    int count = 0; // 게시글 존재 X -> 1번만 출력
    // *** 카테고리 *** -> 1번만 출력
    int categoryInfo = 0;
    int categoryQa = 0;
    int categoryTalk = 0;

    for (Community myPost : communityList) {
      if (myPost.getWriter().getNo() == AuthLogInHandler.getLoginUser().getNo()) {
        count++;

        if (myPost.getCategory() == 1) {
          if (categoryInfo == 0) {
            System.out.println("*** 정보 ***");
          }

          System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
              myPost.getNo(), myPost.getTitle(), myPost.getWriter().getEmail(),
              myPost.getRegisteredDate(), myPost.getViewCount(), myPost.getLike());
          categoryInfo++;
        }
      }
    }

    for (Community myPost : communityList) {
      if (myPost.getWriter().getNo() == AuthLogInHandler.getLoginUser().getNo()) {
        count++;

        if (myPost.getCategory() == 2) {
          if (categoryQa == 0) {
            System.out.println();
            System.out.println("*** 질문 ***");
          }

          System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
              myPost.getNo(), myPost.getTitle(), myPost.getWriter().getEmail(),
              myPost.getRegisteredDate(), myPost.getViewCount(), myPost.getLike());
          categoryQa++;
        }
      }
    }

    for (Community myPost : communityList) {
      if (myPost.getWriter().getNo() == AuthLogInHandler.getLoginUser().getNo()) {
        count++;

        if (myPost.getCategory() == 3) {
          if (categoryTalk == 0) {
            System.out.println();
            System.out.println("*** 스몰톡 ***");
          }

          System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
              myPost.getNo(), myPost.getTitle(), myPost.getWriter().getEmail(),
              myPost.getRegisteredDate(), myPost.getViewCount(), myPost.getLike());
          categoryTalk++;
        }
      }
    }

    if (count == 0) {
      System.out.println("나의 게시글이 존재하지 않습니다.");
      return;
    }
  }
}
