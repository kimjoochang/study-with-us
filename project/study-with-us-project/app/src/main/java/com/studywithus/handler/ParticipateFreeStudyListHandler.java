package com.studywithus.handler;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;

public class ParticipateFreeStudyListHandler implements Command {

  HashMap<String, List<Study>> myParticipatedFreeStudyMap;

  public ParticipateFreeStudyListHandler(HashMap<String, List<Study>> myParticipatedFreeStudyMap) {
    this.myParticipatedFreeStudyMap = myParticipatedFreeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    /* 해쉬맵의 value값을 myParticipatedFreeStudy에 담음 
     * 전역변수로 둘 경우 App 실행 시 getLoginUser() nullPointer 에러뜸 */
    List<Study> myParticipatedFreeStudy = myParticipatedFreeStudyMap.get(AuthLoginHandler.getLoginUser().getId());
    System.out.println("[마이 페이지 / 내가 참여한 무료 스터디]\n");

    if (myParticipatedFreeStudy.isEmpty() == true) {
      System.out.println("참여 무료 스터디가 존재하지 않습니다.\n");
      return;
    }

    // 내가 참여한 무료 스터디 리스트 출력
    for (Study freeStudy : myParticipatedFreeStudy) {
      System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 등록일 = %s, 조회수 = %d, 좋아요 = %d]", 
          freeStudy.getNo(), 
          freeStudy.getTitle(), 
          freeStudy.getWriter().getName(),
          freeStudy.getRegisteredDate(),
          freeStudy.getViewCount(), 
          freeStudy.getLike());
    }
    System.out.println();
  }
}
