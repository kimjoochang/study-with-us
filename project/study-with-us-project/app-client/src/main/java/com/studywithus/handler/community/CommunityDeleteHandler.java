package com.studywithus.handler.community;

import java.util.List;
import com.studywithus.domain.Community;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class CommunityDeleteHandler extends AbstractCommunityHandler{

  public CommunityDeleteHandler(List<Community> communityList) {
    super(communityList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[커뮤니티 / 삭제] \n");

    int no = (int) request.getAttribute("communityNo");

    Community community = findByNo(no);

    if (community == null) {
      System.out.println();
      System.out.println("해당 번호의 게시글이 없습니다.\n");
      return;
    }

    while(true) {
      String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("게시글 삭제를 취소하였습니다.\n");
        return;

      } else if (input.equalsIgnoreCase("y")) {

        communityList.remove(community);
        System.out.println("게시글을 삭제하였습니다.\n");
        return;

      } else {
        System.out.println("올바른 값을 입력하세요.\n");
        continue;
      }
    }
  }
}
