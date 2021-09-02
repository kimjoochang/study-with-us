package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class InterestFreeAddHandler extends AbstractInterestFreeHandler {

  public InterestFreeAddHandler(List<FreeStudy> freeInterestList) {
    super(freeInterestList);
  }
  @Override
  public void execute(FreeStudy freeStudy) {
    String input = Prompt.inputString("무료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }
    freeInterestList.add(freeStudy);

    System.out.println();
    System.out.println("무료 스터디 관심 목록에 추가되었습니다.\n");
  }

}
