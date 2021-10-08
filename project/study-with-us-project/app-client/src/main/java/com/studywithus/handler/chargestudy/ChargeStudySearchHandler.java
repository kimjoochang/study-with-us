package com.studywithus.handler.chargestudy;

import java.util.Collection;
import java.util.HashMap;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ChargeStudySearchHandler implements Command {

  RequestAgent requestAgent;

  public ChargeStudySearchHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 검색]\n");

    String input = Prompt.inputString("검색할 키워드를 입력하세요. > ");
    System.out.println();

    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", String.valueOf(input));

    requestAgent.request("chargeStudy.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("키워드와 일치하는 유료스터디가 없습니다.");
      return;
    }

    Collection<Study> studyList = requestAgent.getObjects(Study.class);

    int count = 0; // 일치하는 값이 없을 경우, 게시글 없음을 한 번만 출력하기 위한 변수

    for (Study chargeStudy : studyList) {
      //      if (!chargeStudy.getTitle().contains(input) &&
      //          !chargeStudy.getContent().contains(input) &&
      //          !chargeStudy.getWriter().getName().contains(input)) {
      //        type = 1;
      //        continue;
      //      }

      System.out.printf("[번호 = %d, 제목 = %s, 멘토 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
          chargeStudy.getNo(), 
          chargeStudy.getTitle(), 
          chargeStudy.getWriter().getName(),
          chargeStudy.getRegisteredDate(),
          chargeStudy.getViewCount(), 
          chargeStudy.getLikeMembers().size());
      count++;
      return;
    } 

    if (count < 1) {
      System.out.println("입력하신 키워드가 포함된 게시글이 없습니다.");
    }
  }
}
