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
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이페이지 / 무료 스터디 / 관심 목록 / 삭제]\n");

    // Study freeInterest = findByNo(no);

    int no = (int) request.getAttribute("no");

    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("freeStudy.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    if (request.getAttribute("freeNo") == null) {
      int menuNo = Prompt.inputInt("메뉴 번호를 입력하세요. > ");

      List<Member> likeMembers = freeInterest.getLikeMembers();
      int type = 0; // 일치하는 값이 없을 경우, 무료 스터디 관심 목록 없다는 출력문이 한 번만 출력되게 하기 위한 변수

      for (Member member : likeMembers) {
        if (member.getNo().equals(AuthLogInHandler.getLoginUser().getNo())) {
          type = 1;
          break;
        }
      }

      if (type == 0) {
        System.out.println("해당 번호의 관심목록이 없습니다.");
        return;
      }
      while (true) {
        String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

        if (input.equalsIgnoreCase("n") || input.length() == 0) {
          System.out.println("무료 스터디 관심 목록을 취소하였습니다.");
          return;

        } else if (!input.equalsIgnoreCase("y")) {
          System.out.println("다시 입력하세요.\n");
          continue;

        } else {
          break;
        }
      }

      likeUsers.remove(AuthLogInHandler.getLoginUser());
      freeInterest.setLikeMembers(likeUsers);

      System.out.println();
      System.out.println("무료 스터디 관심 목록을 삭제하였습니다.\n");

    } else {
      int no = (int) request.getAttribute("freeNo");

      // Study freeInterest = findByNo(no);

      // if (freeInterest == null) {
      // System.out.println("해당 번호의 무료 스터디 관심 목록이 없습니다.");
      // return;
      // }

      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("해당 번호의 무료 스터디 관심 목록이 없습니다.");
        return;
      }

      // if (freeInterest.getLikeMembers() == null) {
      // System.out.println("무료 스터디 관심목록이 존재하지 않습니다.\n");
      // return;
      // }

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

      // List<Member> likeUsers = Member.getLikeMembers();
      // likeUsers.remove(AuthLogInHandler.getLoginUser());
      // freeInterest.setLikeMembers(likeUsers);

      requestAgent.request("freeStudyInterest.delete", params);
      if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("무료 스터디 관심 목록 삭제 실패!");
        System.out.println(requestAgent.getObject(String.class));
        return;
      }

      System.out.println();
      System.out.println("무료 스터디 관심 목록을 삭제하였습니다.");
    }
  }
}

