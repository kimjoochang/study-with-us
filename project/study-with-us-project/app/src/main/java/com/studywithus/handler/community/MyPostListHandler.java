package com.studywithus.handler.community;

import java.util.ArrayList;
import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class MyPostListHandler implements Command {

  List<Community> myPostList = new ArrayList<>();

  List<Community> communityQaList;
  List<Community> communityInfoList;
  List<Community> communityTalkList;

  public MyPostListHandler(List<Community> communityQaList, List<Community> communityInfoList, List<Community> communityTalkList) {
    this.communityQaList = communityQaList;
    this.communityInfoList = communityInfoList;
    this.communityTalkList = communityTalkList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[마이 페이지 / 나의 활동 / 내 게시글 / 내 게시글 조회]\n");

    myPostList.addAll(communityQaList);
    myPostList.addAll(communityInfoList);
    myPostList.addAll(communityTalkList);

    for (Community myPost : myPostList) {
      System.out.println(myPost.getWriter().getName());
      if (myPost.getWriter().getId().equals(AuthLogInHandler.getLoginUser().getId())) {
        System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
            myPost.getNo(),
            myPost.getTitle(),
            myPost.getWriter().getName(),
            myPost.getRegisteredDate(), 
            myPost.getViewCount(),
            myPost.getLike());
      } else {
        System.out.println("나의 게시글이 존재하지 않습니다.\n");
        return;
      }
    } 
  }

  // ID로 회원별 커뮤니티 게시글 조회
  //  public Community findmyPostById(String id) {
  //    for (Community writer : communityList) {
  //      if (writer.getWriter().getId().equals(id)) {
  //        return writer;
  //      }
  //    }
  //    return null;
  //  }
}

