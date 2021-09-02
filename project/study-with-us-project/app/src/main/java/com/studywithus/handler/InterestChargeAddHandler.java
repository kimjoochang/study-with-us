package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ChargeStudy;
import com.studywithus.util.Prompt;

public class InterestChargeAddHandler extends AbstractInterestChargeHandler {


  public InterestChargeAddHandler(List<ChargeStudy> chargeInterestList) {
    super(chargeInterestList);
  }

  // 유료 스터디 관심 목록 추가
  @Override
  public void execute(ChargeStudy study) {
    String input = Prompt.inputString("관심 목록에 추가하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("유료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }

    chargeInterestList.add(study);

    System.out.println();
    System.out.println("유료 스터디 관심 목록에 추가되었습니다.\n");
    return;
  }

}
