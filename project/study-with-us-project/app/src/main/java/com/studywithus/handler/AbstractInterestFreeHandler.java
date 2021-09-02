package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;

public abstract class AbstractInterestFreeHandler implements Command{

  public List<FreeStudy> freeInterestList;

  public AbstractInterestFreeHandler(List<FreeStudy> freeInterestList) {
    this.freeInterestList = freeInterestList;
  }

  @Override
  public void execute() {
  }

  protected void execute(FreeStudy freeStudy) {
  };

  protected FreeStudy findByNo(int no) {
    for (FreeStudy freeStudy : freeInterestList) {
      if (freeStudy.getNo() == no) {
        return freeStudy;
      }
    }
    return null;
  }


}
