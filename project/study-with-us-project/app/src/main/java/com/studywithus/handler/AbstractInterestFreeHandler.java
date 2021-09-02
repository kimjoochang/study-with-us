package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;

public abstract class AbstractInterestFreeHandler implements Command{

  protected List<FreeStudy> freeInterestList;

  public AbstractInterestFreeHandler(List<FreeStudy> freeInterestList) {
    this.freeInterestList = freeInterestList;
  }

  abstract void execute(FreeStudy freeStudy);

  protected FreeStudy findByNo(int no) {
    for (FreeStudy freeStudy : freeInterestList) {
      if (freeStudy.getNo() == no) {
        return freeStudy;
      }
    }
    return null;
  }

}
