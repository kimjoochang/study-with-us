package com.studywithus.handler.community;

import java.util.Collection;
import java.util.HashMap;
import com.studywithus.domain.Community;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class CommunitySearchHandler implements Command {

  RequestAgent requestAgent;

  public CommunitySearchHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[커뮤니티 / 검색]\n");

    String input = Prompt.inputString("검색할 키워드를 입력하세요. > ");
    System.out.println();

    HashMap<String, String> params = new HashMap<>();
    params.put("keyword", String.valueOf(input));

    requestAgent.request("community.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("커뮤니티 게시글이 존재하지 않습니다.\n");
      return;
    }

    // [기존] 키워드 포함 게시글이 없을 경우, line 43 출력문이 무한 반복됨
    // [수정] 임의의 변수로 조건문 설정하여 한 번만 출력되도록 수정
    int type = 0;

    Collection<Community> communityList = requestAgent.getObjects(Community.class);

    for (Community community : communityList) {
      if (!community.getTitle().contains(input) && !community.getContent().contains(input)
          && !community.getWriter().getName().contains(input)) {
        type = 1;
        continue;
      }

      System.out.printf("[번호 = %d, 제목 = %s, 작성자 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
          community.getNo(), community.getTitle(), community.getWriter().getName(),
          community.getRegisteredDate(), community.getViewCount(), community.getLike());
      return;
    }

    if (type == 1) {
      System.out.println("입력하신 키워드가 포함된 게시글이 없습니다.\n");
    }
  }
}
