package com.studywithus.handler.study;

import java.util.HashMap;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class FreeStudyApplyDetailHandler implements Command {

  RequestAgent requestAgent;

  public FreeStudyApplyDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이페이지 / 나의 활동 / 무료 스터디 신청 내역 / 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");

    // Study freeStudy = findByNo(no);

    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("freeStudy.selectOne", params);

    // if (freeStudy == null) {
    // System.out.println();
    // System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
    // return;
    // }

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 신청 내역이 없습니다.");
      return;
    }

    Study freeStudy = requestAgent.getObject(Study.class);
    freeStudy.setViewCount(freeStudy.getViewCount() + 1);

    System.out.printf("제목: %s\n", freeStudy.getTitle());
    System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

    if (freeStudy.getArea() != null) {
      System.out.printf("온/오프라인: %s\n", freeStudy.getOFFLINE());
      System.out.printf("지역: %s\n", freeStudy.getArea());

    } else {
      System.out.printf("온/오프라인: %s\n", freeStudy.getONLINE());
    }

    System.out.printf("시작일: %s\n", freeStudy.getStartDate());
    System.out.printf("종료일: %s\n", freeStudy.getEndDate());
    System.out.printf("모집인원: %d / %d\n", freeStudy.getMembers().size(), freeStudy.getMaxMembers());
    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

    freeStudy.setViewCount(freeStudy.getViewCount() + 1);
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());
    System.out.printf("좋아요: %d\n", freeStudy.getLikeMembers().size());
    System.out.println();

    // CommandRequest에 보관 -> 게시글 번호 사용 가능
    request.setAttribute("freeNo", no);

    while (true) {
      System.out.println("1. 신청 취소");
      System.out.println("0. 이전");
      System.out.println();

      int menuNo = Prompt.inputInt("메뉴 번호를 선택하세요. > ");
      System.out.println();

      if (menuNo == 1) {
        request.getRequestDispatcher("/freeStudy/applyCancel").forward(request);

      } else if (menuNo == 0) {
        return;

      } else {
        System.out.println("다시 입력하세요.\n");
        continue;
      }
      return;
    }
  }
}
