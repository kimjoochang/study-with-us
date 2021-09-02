package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.util.Prompt;

public class CommunityInfoDeleteHandler extends AbstractCommunityHandler{

  public CommunityInfoDeleteHandler(List<Community> communityList) {
    super(communityList);
  }


  // 정보 게시글 삭제
  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 정보 게시글 삭제]");

    int no = Prompt.inputInt("번호? ");

    Community community = findByNo(no);

    if (community == null) {
      System.out.println();
      System.out.println("해당 번호의 정보 게시글이 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("정보 게시글 삭제를 취소하였습니다.");
      return;
    }

    communityList.remove(community);

    System.out.println();
    System.out.println("정보 게시글을 삭제하였습니다.\n");
  }

}
