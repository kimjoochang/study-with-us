package com.studywithus.handler.chargestudy;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ChargeStudyInterestDeleteHandler extends AbstractStudyHandler {

  Study chargeStudy;

  // 유료 스터디 관심목록 리스트 (회원 관점)
  List<Study> chargeInterestList;

  public ChargeStudyInterestDeleteHandler(List<Study> chargeStudyList, List<Study> chargeInterestList) {
    super(chargeStudyList);
    this.chargeInterestList = chargeInterestList;
  }

  @Override
  public void execute(CommandRequest request) {
    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("유료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    chargeInterestList.remove(chargeStudy);
    chargeStudy.setViewCount(chargeStudy.getLike() - 1);

    System.out.println();
    System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");
  }
}
