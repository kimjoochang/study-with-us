package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public abstract class AbstractChargeStudyHandler implements Command {

  protected List<Study> chargeStudyList;

  public AbstractChargeStudyHandler(List<Study> chargeStudyList) {
    this.chargeStudyList = chargeStudyList;
  }

  // 유료 스터디 게시글 번호 조회
  protected Study findByNo(int no) {
    for (Study chargeStudy : chargeStudyList) {
      if (chargeStudy.getNo() == no) {
        return chargeStudy;
      }
    }
    return null;
  }
}
