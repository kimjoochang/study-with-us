package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;

public abstract class AbstractMyStudy implements Command{

  Study freeStudy;
  /* 해쉬맵의 value값을 myParticipatedFreeStudy에 담음 
   * 전역변수로 둘 경우 App 실행 시 getLoginUser() nullPointer 에러뜸 */
  List<Study> myParticipatedFreeStudy;

  public AbstractMyStudy(List<Study> myParticipatedFreeStudy) {
    this.myParticipatedFreeStudy = myParticipatedFreeStudy;
  }


}
