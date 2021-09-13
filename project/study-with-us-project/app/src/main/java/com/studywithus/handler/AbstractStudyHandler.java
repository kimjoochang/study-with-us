package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Study;

public abstract class AbstractStudyHandler implements Command {

  protected List<Study> studyList;

  public AbstractStudyHandler(List<Study> studyList) {
    this.studyList = studyList;
  }

  // 스터디 게시글 번호 조회
  protected Study findByNo(int no) {
    for (Study study : studyList) {
      if (study.getNo() == no) {
        return study;
      }
    }
    return null;
  }
}
