package com.studywithus.handler.community;

import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class MyPostListHandler extends AbstractCommunityHandler {

  List<Community> communityList;

  public MyPostListHandler(List<Community> communityList) {
    super(communityList);
  }

  // ver1
  //  @Override
  //  public void execute() {
  //    System.out.println("[마이 페이지 / 나의 활동 / 내 게시글 / 내 게시글 조회]\n");
  //
  //    if (Community.getWriter().getId() == AuthLoginHandler.getLoginUser()) {
  //
  //      for (Community community : communityList) {
  //
  //        System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
  //            community.getNo(),
  //            community.getTitle(),
  //            community.getWriter().getName(),
  //            community.getRegisteredDate(), 
  //            community.getViewCount(),
  //            community.getLike());
  //        System.out.println();
  //      } 
  //    } else {
  //
  //    System.out.println();
  //    System.out.println("내 게시글이 존재하지 않습니다.\n");
  //        return;
  //    }
  //  }

  // ver2
  @Override
  public void execute(CommandRequest request) {
    System.out.println("[마이 페이지 / 나의 활동 / 내 게시글 / 내 게시글 조회]\n");

    String id = AuthLogInHandler.getLoginUser().getId();
    Community cmnt = findById(id);

    if (cmnt == null) {
      System.out.println("내 게시글이 존재하지 않습니다.\n");
      return;
    }

    for (Community myPost : communityList) {
      System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]", 
          myPost.getNo(),
          myPost.getTitle(),
          myPost.getWriter().getId(),
          myPost.getRegisteredDate(), 
          myPost.getViewCount(),
          myPost.getLike());
    } 
    System.out.println();
  }

  // ID로 회원별 커뮤니티 게시글 조회
  public Community findById(String id) {
    for (Community writer : communityList) {
      if (writer.getWriter().getId().equals(id)) {
        return writer;
      }
    }
    return null;
  }
}
