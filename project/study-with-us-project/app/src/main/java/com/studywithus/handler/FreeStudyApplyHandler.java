package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.FreeStudy;

public class FreeStudyApplyHandler extends AbstractFreeStudyHandler {

  public FreeStudyApplyHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);
  }

  // 무료 스터디 신청
  public void execute() {
    System.out.println("[무료 스터디 신청]");
  }
}
