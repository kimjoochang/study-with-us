package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.util.Prompt;

public class CommunityInfoUpdateHandler extends AbstractCommunityHandler{

  public CommunityInfoUpdateHandler(List<Community> communityList) {
    super(communityList);
  }

  // 정보 게시글 수정
  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 정보 게시글 수정]");

    int no = Prompt.inputInt("번호? ");

    Community community = findByNo(no);

    if (community == null) {
      System.out.println();
      System.out.println("해당 번호의 정보 게시글이 없습니다.\n");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", community.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", community.getContent()));

    String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("정보 게시글 수정을 취소하였습니다.");
      return;
    }

    community.setTitle(title);
    community.setContent(content);

    System.out.println();
    System.out.println("정보 게시글을 수정하였습니다.\n");
  }

}
