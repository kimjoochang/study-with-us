package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyInterestAddHandler extends AbstractStudyHandler {


  public FreeStudyInterestAddHandler(List<Study> freeStudyList) {
    super(freeStudyList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 / 상세보기 / 관심 목록]\n");
    int no = (int) request.getAttribute("freeNo");

    Study freeInterest = findByNo(no);


    if (freeInterest == null) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    String input = Prompt.inputString("무료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }
    List<Member> likeMember = freeInterest.getLikeMembers();
    likeMember.add(AuthLogInHandler.getLoginUser());
    freeInterest.setLikeMembers(likeMember);

    System.out.println();
    System.out.println("무료 스터디 관심 목록에 추가되었습니다.\n");
  }
}