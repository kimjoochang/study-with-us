package com.studywithus.handler.study;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
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

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이페이지 / 무료 스터디 / 관심 목록 / 삭제]\n");
    int no = (int) request.getAttribute("no");

    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("freeStudy.interest.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 무료 스터디 관심 목록이 없습니다.");
      return;
    }

    int type = 0; // 일치하는 값이 없을 경우, 게시글 없다는 출력문이 한 번만 출력되게 하기 위한 변수

    if (request.getAttribute("freeNo") == null) {
      int num = Prompt.inputInt("메뉴 번호를 입력하세요. > ");

      // Study freeInterest = findByNo(no);

      for (Member member : freeInterest.getLikeMembers()) {
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

      List<Member> likeMember = freeInterest.getLikeMembers();
      likeMember.remove(AuthLogInHandler.getLoginUser());
      freeInterest.setLikeMembers(likeMember);

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

      if (freeInterest.getLikeMembers() == null) {
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

      List<Member> likeUser = freeInterest.getLikeMembers();
      likeUser.remove(AuthLogInHandler.getLoginUser());
      freeInterest.setLikeMembers(likeUser);

      requestAgent.request("freeStudy.interest.delete", freeInterest);

      System.out.println();
      System.out.println("무료 스터디 관심 목록을 삭제하였습니다.");
    }
  }
}

