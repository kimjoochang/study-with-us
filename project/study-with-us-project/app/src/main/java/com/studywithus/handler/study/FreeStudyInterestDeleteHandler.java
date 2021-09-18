package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class FreeStudyInterestDeleteHandler extends AbstractStudyHandler {
  List<Study> freeInterestList;

  public FreeStudyInterestDeleteHandler(List<Study> freeStudyList, List<Study> freeInterestList) {
    super(freeStudyList);
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

    // 상세보기 시 조회수 1 추가
    freeInterest.setViewCount(freeInterest.getViewCount() + 1);

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    freeInterestList.remove(freeInterest);
    freeInterest.setLike(freeInterest.getLike() - 1);

    System.out.println();
    System.out.println("무료 스터디 관심 목록을 삭제하였습니다.\n");
  }
}
