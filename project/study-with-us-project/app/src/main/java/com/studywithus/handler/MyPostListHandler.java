package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.domain.Member;

public class MyPostListHandler extends AbstractCommunityHandler {

  List<Community> communityList;
  List<Member> communityWriterList;

  public MyPostListHandler(List<Community> communityList, List<Member> communityWriterList) {
    super(communityList);
    this.communityWriterList = communityWriterList;
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
  //        return;
  //      } 
  //    }
  //
  //    System.out.println();
  //    System.out.println("내 게시글이 존재하지 않습니다.\n");
  //  }

  // ver2

  @Override
  public void execute() {
    System.out.println("[마이 페이지 / 나의 활동 / 내 게시글 / 내 게시글 조회]\n");


    Member id = AuthLoginHandler.getLoginUser();
    Community community = findById(id);

    if () {

      for (Community community : communityList) {

        System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
            community.getNo(),
            community.getTitle(),
            community.getWriter().getName(),
            community.getRegisteredDate(), 
            community.getViewCount(),
            community.getLike());
        System.out.println();
        return;
      } 
    }

    System.out.println();
    System.out.println("내 게시글이 존재하지 않습니다.\n");
  }

  // ID로 회원별 커뮤니티 게시글 조회
  public Community findById(String id) {
    for (Community cmnt : communityList) {
      if (cmnt.getWriter().getId().equals(id)) {
        return cmnt;
      }
    }
    return null;
  }

}
