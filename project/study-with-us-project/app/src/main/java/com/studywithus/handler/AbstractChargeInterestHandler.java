package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public abstract class AbstractChargeInterestHandler implements Command {

  protected List<Study> chargeInterestList;

  public AbstractChargeInterestHandler(List<Study> chargeInterestList) {
    this.chargeInterestList = chargeInterestList;
  }

  // 유료 스터디 관심목록 번호 조회
  protected Study findByNo(int no) {
    for (Study chargeInterest : chargeInterestList) {
      if (chargeInterest.getNo() == no) {
        return chargeInterest;
      }
    }
    return null;
  }
}
