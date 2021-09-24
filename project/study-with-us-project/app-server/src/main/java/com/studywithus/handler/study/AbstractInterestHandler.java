package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;

public abstract class AbstractInterestHandler implements Command {

  protected List<Study> interestList;

  public AbstractInterestHandler(List<Study> interestList) {
    this.interestList = interestList;
  }

  // 스터디 관심목록 번호 조회
  protected Study findByNo(int no) {
    for (Study chargeInterest : interestList) {
      if (chargeInterest.getNo() == no) {
        return chargeInterest;
      }
    }
    return null;
  }
}
