package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;

public abstract class AbstractFreeStudyHandler implements Command {

  protected List<FreeStudy> freeStudyList;
  protected List<FreeStudy> freeStudyApplyList;
  protected List<FreeStudy> freeInterestList;

  public AbstractFreeStudyHandler(List<FreeStudy> freeStudyList) {
    this.freeStudyList = freeStudyList;
  }

  public AbstractFreeStudyHandler(List<FreeStudy> freeStudyList, List<FreeStudy> freeStudyApplyList, List<FreeStudy> freeInterestList) {
    this.freeStudyList = freeStudyList;
    this.freeStudyApplyList = freeStudyApplyList;
    this.freeInterestList = freeInterestList;
  }

  // 무료 스터디 번호 조회
  protected FreeStudy findByNo(int no) {
    for (FreeStudy freeStudy : freeStudyList) {
      if (freeStudy.getNo() == no) {
        return freeStudy;
      }
    }
    return null;
  }
}
