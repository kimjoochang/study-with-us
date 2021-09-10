package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public abstract class AbstractInterestHandler implements Command {

  protected List<Study> freeInterestList;
  protected List<Study> chargeInterestList;

  public AbstractInterestHandler(List<Study> freeInterestList, List<Study> chargeInterestList) {
    this.freeInterestList = freeInterestList;
    this.chargeInterestList = chargeInterestList;
  }

  protected void execute(Study study) {
  }

  //  protected Study findByNo(int no) {
  //    for (Study Study : InterestList) {
  //      if (Study.getNo() == no) {
  //        return Study;
  //      }
  //    }
  //    return null;
  //  }
}
