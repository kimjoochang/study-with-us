package com.studywithus.handler.community;

import java.util.List;
import com.studywithus.domain.Community;
//import com.studywithus.domain.Member;
import com.studywithus.handler.CommandRequest;
//import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class CommunityDetailHandler extends AbstractCommunityHandler{

  public CommunityDetailHandler(List<Community> communityList) {
    super(communityList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[커뮤니티 / 상세보기] \n");

    int no = Prompt.inputInt("번호? ");
    Community community = findByNo(no);

    if (community == null) {
      System.out.println();
      System.out.println("해당 번호의 게시글이 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", community.getTitle());
    System.out.printf("내용: %s\n", community.getContent());
    System.out.printf("작성자: %s\n", community.getWriter().getName());
    System.out.printf("등록일: %s\n", community.getRegisteredDate());

    community.setViewCount(community.getViewCount() + 1);
    System.out.printf("조회수: %d\n", community.getViewCount());

    System.out.println();

    // * 커뮤니티 게시판 적용 보류 *
    //
    // - 질문 / 정보 / 스몰톡 게시판별로 게시글이 다루어져서
    // - request.getRequestDispatcher에 키 값 다르게 적용해야 함 -> 불가능일듯
    // -> OR 리스트 따로 만들거나 핸들러 분리해야 해서 논의해야 함 -> 논의 필요

    /* 
    Member loginUser = AuthLogInHandler.getLoginUser(); 
    if (loginUser == null || community.getWriter().getId() != loginUser.getId()) {
      return;
    }

    // CommunityUpdateHandler나 CommunityStudyDeleteHandler를 실행할 때 
    // 게시글 번호를 사용할 수 있도록 CommandRequest에 보관한다.
    request.setAttribute("no", no);

    while (true) {
      String input = Prompt.inputString("변경(U), 삭제(D), 이전(0)>");
      switch (input) {
        case "U":
        case "u":
          request.getRequestDispatcher("/communityQa/update").forward(request);
          return;
        case "D":
        case "d":
          request.getRequestDispatcher("/communityQa /delete").forward(request);
          return;
        case "0":
          return;
        default:
          System.out.println("명령어가 올바르지 않습니다!");
      }
    }
     */
  }
}
