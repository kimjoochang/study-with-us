package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;

public abstract class AbstractFreeInterestHandler implements Command{

  protected List<FreeStudy> freeInterestList;

  public AbstractFreeInterestHandler(List<FreeStudy> freeInterestList) {
    this.freeInterestList = freeInterestList;
  }

  abstract void execute(FreeStudy freeStudy);

  @Override
  public void execute() {

  }

}
