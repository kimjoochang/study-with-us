package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeInterestAddHandler extends AbstractFreeInterestHandler {

  public FreeInterestAddHandler(List<FreeStudy> freeInterestList) {
    super(freeInterestList);
  }

  // 무료 스터디 관심 목록 추가
  @Override
  public void execute(FreeStudy freestudy) {
    String input = Prompt.inputString("관심 목록에 추가하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }

    freeInterestList.add(freestudy);

    System.out.println();
    System.out.println("무료 스터디 관심 목록에 추가되었습니다.\n");
    return;
  }
}