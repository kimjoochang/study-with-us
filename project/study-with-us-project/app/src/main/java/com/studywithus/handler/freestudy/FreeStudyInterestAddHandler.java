package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class FreeStudyInterestAddHandler extends AbstractStudyHandler {

  List<Study> freeInterestList;

  public FreeStudyInterestAddHandler(List<Study> studyList, List<Study> freeInterestList) {
    super(studyList);
    this.freeInterestList = freeInterestList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 / 상세보기 / 관심 목록]\n");
    int no = (int) request.getAttribute("no");

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

    freeInterestList.add(freeInterest);

    // 무료 스터디 관심 목록에 좋아요한 무료 스터디 추가 (회원 관점)
    freeInterest.setLike(freeInterest.getLike() + 1);

    System.out.println();
    System.out.println("무료 스터디 관심 목록에 추가되었습니다.\n");
  }
}