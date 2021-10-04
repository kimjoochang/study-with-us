package com.studywithus.handler.study;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class FreeStudyInterestDeleteHandler implements Command {

  RequestAgent requestAgent;

  public FreeStudyInterestDeleteHandler(RequestAgent requestAgent) {
    // super(freeStudyList);
    this.requestAgent = requestAgent;
  }

  //  HashMap<String, List<Study>> hm = new HashMap<>();

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이페이지 / 무료 스터디 / 관심 목록 / 삭제]\n");
    int no = (int) request.getAttribute("freeNo");

    HashMap<String, String> params = new HashMap<>();
    params.put("freeNo", String.valueOf(no));

    requestAgent.request("freeStudy.interest.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 무료 스터디 관심 목록이 없습니다.");
      return;
    }

    int type = 0; // 일치하는 값이 없을 경우, 게시글 없다는 출력문이 한 번만 출력되게 하기 위한 변수
    Study freeStudyInterest = requestAgent.getObject(Study.class);

    if (request.getAttribute("freeNo") == null) {
      // [질문] FreeStudyDetailHandler와의 차이
      //      int num = Prompt.inputInt("메뉴 번호를 입력하세요. > ");

      // Study freeInterest = findByNo(no);

      for (Member member : freeStudyInterest.getLikeMembers()) {
        if (member.getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
          type = 1;
          break;
        }
      }

      if (type == 0) {
        System.out.println("해당 번호의 관심 목록이 없습니다.");
        return;
      }


      while (true) {
        String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

        if (input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
          return;

        } else if (!input.equalsIgnoreCase("y")) {
          System.out.println("다시 입력하세요.\n");
          continue;

        } else {
          break;
        }
      }

      List<Member> likeUser = freeStudyInterest.getLikeMembers();
      likeUser.remove(AuthLogInHandler.getLoginUser());
      freeStudyInterest.setLikeMembers(likeUser);

      System.out.println();
      System.out.println("무료 스터디 관심 목록을 삭제하였습니다.\n");

    } else {
      int num = (int) request.getAttribute("freeNo");

      // Study freeInterest = findByNo(no);

      // if (freeInterest == null) {
      // System.out.println("해당 번호의 게시글이 없습니다.");
      // return;
      // }

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 게시글이 없습니다.");
        return;
      }

      if (freeStudyInterest.getLikeMembers() == null) {
        System.out.println("무료 스터디 관심 목록이 존재하지 않습니다.\n");
        return;
      }

      while (true) {
        String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

        if (input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
          return;

        } else if (!input.equalsIgnoreCase("y")) {
          System.out.println("다시 입력하세요.\n");
          continue;

        } else {
          break;
        }
      }

      List<Member> likeUser = freeStudyInterest.getLikeMembers();
      likeUser.remove(AuthLogInHandler.getLoginUser());
      freeStudyInterest.setLikeMembers(likeUser);

      requestAgent.request("freeStudy.interest.delete", freeStudyInterest);

      System.out.println();
      System.out.println("무료 스터디 관심 목록을 삭제하였습니다.");
    }
  }
}

