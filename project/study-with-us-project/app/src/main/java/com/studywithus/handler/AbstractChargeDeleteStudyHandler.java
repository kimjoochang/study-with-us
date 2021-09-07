package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ChargeStudy;

public abstract class AbstractChargeDeleteStudyHandler implements Command {
  protected List<ChargeStudy> chargeInterestList;

  public AbstractChargeInterestHandler(List<ChargeStudy> chargeInterestList) {
    this.chargeInterestList = chargeInterestList;
  }
}
