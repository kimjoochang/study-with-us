package com.studywithus.handler.copy;

import java.util.List;
import com.studywithus.domain.Study;

public abstract class AbstractChargeInterestHandler implements Command{

  protected List<Study> chargeInterestList;

  public AbstractChargeInterestHandler(List<Study> chargeInterestList) {
    this.chargeInterestList = chargeInterestList;
  }

  @Override
  public void execute() {
  }

  protected void execute(Study study) {
  }

  protected Study findByNo(int no) {
    for (Study study : chargeInterestList) {
      if (study.getNo() == no) {
        return study;
      }
    }
    return null;
  }
}
