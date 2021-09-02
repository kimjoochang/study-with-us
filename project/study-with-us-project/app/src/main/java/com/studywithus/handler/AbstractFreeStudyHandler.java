package com.studywithus.handler;

import com.studywithus.domain.FreeStudy;

public abstract class AbstractFreeStudyHandler implements Command {

  static final int MAX_LENGTH = 5;

  FreeStudy[] studies = new FreeStudy[MAX_LENGTH];
  int size = 0;

  // 무료 스터디 번호 조회
  protected FreeStudy findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.studies[i].getNo() == no) {
        return this.studies[i];
      }
    }
    return null;
  }

  // 무료 스터디 조회
  protected int indexOf(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.studies[i].getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
