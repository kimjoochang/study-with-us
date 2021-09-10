package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public abstract class AbstractStudyHandler implements Command {

  protected List<Study> freeStudyList;
  protected List<Study> chargeStudyList;

  public AbstractStudyHandler(List<Study> freeStudyList, List<Study> chargeStudyList) {
    this.freeStudyList = freeStudyList;
    this.chargeStudyList = chargeStudyList;
  }

  protected Study findByNo(int no) {
    // 무료 스터디 번호 조회
    if (Study.value) {
      for (Study freeStudy : freeStudyList) {
        if (freeStudy.getNo() == no) {
          return freeStudy;
        }
      }
    }

    // 유료 스터디 번호 조회
    else {
      for (Study freeStudy : chargeStudyList) {
        if (freeStudy.getNo() == no) {
          return freeStudy;
        }
      }
    }
    return null;
  }
}
