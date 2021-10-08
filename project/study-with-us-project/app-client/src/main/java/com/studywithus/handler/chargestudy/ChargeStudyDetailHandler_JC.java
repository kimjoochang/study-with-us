package com.studywithus.handler.chargestudy;

import java.util.HashMap;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ChargeStudyDetailHandler_JC implements Command {

  RequestAgent requestAgent;
  ChargeStudyDetailMenuPrompt chargeStudyDetailMenuPrompt;

  public ChargeStudyDetailHandler_JC(RequestAgent requestAgent, ChargeStudyDetailMenuPrompt chargeStudyDetailMenuPrompt) {
    this.requestAgent = requestAgent;
    this.chargeStudyDetailMenuPrompt = chargeStudyDetailMenuPrompt;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("chargeStudy.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
      return;
    }

    Study chargeStudy = requestAgent.getObject(Study.class);

    chargeStudy.setViewCount(chargeStudy.getViewCount() + 1);

    System.out.printf("제목: %s\n", chargeStudy.getTitle());
    System.out.printf("멘토: %s\n", chargeStudy.getWriter().getName());

    System.out.printf("설명: %s\n", chargeStudy.getContent());
    System.out.printf("지역: %s\n", chargeStudy.getArea());
    System.out.printf("가격: %s\n", chargeStudy.getPrice());
    System.out.printf("시작일: %s\n", chargeStudy.getStartDate());
    System.out.printf("종료일: %s\n", chargeStudy.getEndDate());
    System.out.printf("스터디 진행상태: %s\n", chargeStudyDetailMenuPrompt.studyStatus(chargeStudy));
    System.out.printf("등록일: %s\n", chargeStudy.getRegisteredDate());

    System.out.printf("모집인원 = %d / %d\n", chargeStudy.getMembers().size(), chargeStudy.getMaxMembers());
    System.out.printf("조회수: %d\n", chargeStudy.getViewCount());
    System.out.printf("좋아요수: %d\n", chargeStudy.getLikeMembersEmail().size());
    System.out.println();

    request.setAttribute("chargeNo", no);

    // 본인이 작성한 글 상세보기 시 경우 보이는 메뉴
    if (chargeStudy.getWriter().getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {

      chargeStudyDetailMenuPrompt.myStudySelectedMenu();

      // 타인이 작성한 글 상세보기 시 보이는 메뉴
    } else {
      chargeStudyDetailMenuPrompt.anotherStudySelectedMenu();

    }
  }

}

