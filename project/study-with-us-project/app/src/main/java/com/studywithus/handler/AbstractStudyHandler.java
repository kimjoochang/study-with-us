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

  // 무료 스터디 번호 조회
  //  protected Study findByNo(int no) {
  //    for (Study Study : StudyList) {
  //      if (Study.getNo() == no) {
  //        return Study;
  //      }
  //    }
  //    return null;
  //  }
}
