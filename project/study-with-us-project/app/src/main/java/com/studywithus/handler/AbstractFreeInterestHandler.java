package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;

public abstract class AbstractFreeInterestHandler implements Command {

  protected List<FreeStudy> freeInterestList;

  public AbstractFreeInterestHandler(List<FreeStudy> freeInterestList) {
    this.freeInterestList = freeInterestList;
  }

  protected void execute(FreeStudy study) {
  }

  protected FreeStudy findByNo(int no) {
    for (FreeStudy freeStudy : freeInterestList) {
      if (freeStudy.getNo() == no) {
        return freeStudy;
      }
    }
    return null;
  }
}
