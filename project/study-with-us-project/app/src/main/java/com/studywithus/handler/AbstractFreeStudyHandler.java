package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public abstract class AbstractFreeStudyHandler implements Command {

  protected List<Study> freeStudyList;

  public AbstractFreeStudyHandler(List<Study> freeStudyList) {
    this.freeStudyList = freeStudyList;
  }

  // 무료 스터디 번호 조회
  protected Study findByNo(int no) {
    for (Study freeStudy : freeStudyList) {
      if (freeStudy.getNo() == no) {
        return freeStudy;
      }
    }
    return null;
  }
}
