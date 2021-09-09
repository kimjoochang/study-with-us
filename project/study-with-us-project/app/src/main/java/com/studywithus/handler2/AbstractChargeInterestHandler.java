package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ChargeStudy;

public abstract class AbstractChargeInterestHandler implements Command{

  protected List<ChargeStudy> chargeInterestList;

  public AbstractChargeInterestHandler(List<ChargeStudy> chargeInterestList) {
    this.chargeInterestList = chargeInterestList;
  }

  @Override
  public void execute() {
  }

  protected void execute(ChargeStudy study) {
  }

  protected ChargeStudy findByNo(int no) {
    for (ChargeStudy chargeStudy : chargeInterestList) {
      if (chargeStudy.getNo() == no) {
        return chargeStudy;
      }
    }
    return null;
  }
}
