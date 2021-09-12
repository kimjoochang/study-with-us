package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public abstract class AbstractFreeInterestHandler implements Command {

  protected List<Study> freeInterestList;

  public AbstractFreeInterestHandler(List<Study> freeInterestList) {
    this.freeInterestList = freeInterestList;
  }

  // 무료 스터디 관심목록 번호 조회
  protected Study findByNo(int no) {
    for (Study study : freeInterestList) {
      if (study.getNo() == no) {
        return study;
      }
    }
    return null;
  }
}
