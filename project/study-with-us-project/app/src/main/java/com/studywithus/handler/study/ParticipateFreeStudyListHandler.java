package com.studywithus.handler.study;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class ParticipateFreeStudyListHandler implements Command {

  HashMap<String, List<Study>> participateFreeStudyMap;

  public ParticipateFreeStudyListHandler(HashMap<String, List<Study>> participateFreeStudyMap) {
    this.participateFreeStudyMap = participateFreeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    /* 해쉬맵의 value값을 myParticipatedFreeStudy에 담음 
     * 전역변수로 둘 경우 App 실행 시 getLoginUser() nullPointer 에러뜸 */
    List<Study> myParticipatedFreeStudy = participateFreeStudyMap.get(AuthLogInHandler.getLoginUser().getId());
    System.out.println("[마이 페이지 / 내가 참여한 무료 스터디]\n");

    if (myParticipatedFreeStudy.isEmpty() == true) {
      System.out.println("참여 무료 스터디가 존재하지 않습니다.\n");
      return;
    }

    // 내가 참여한 무료 스터디 리스트 출력
    for (Study freeStudy : myParticipatedFreeStudy) {
      if (freeStudy.getOnOffLine() == 1) {
        System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
            freeStudy.getNo(), 
            freeStudy.getTitle(), 
            freeStudy.getWriter().getName(),
            freeStudy.getONLINE(),
            freeStudy.getMembers().size(),
            freeStudy.getMaxMembers(),
            freeStudy.getRegisteredDate(),
            freeStudy.getViewCount(), 
            freeStudy.getLikeMembers().size());
      } else {
        System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, 지역 = %s, 모집인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n", 
            freeStudy.getNo(), 
            freeStudy.getTitle(), 
            freeStudy.getWriter().getName(),
            freeStudy.getOFFLINE() ,
            freeStudy.getArea(),
            freeStudy.getMembers().size(),
            freeStudy.getMaxMembers(),
            freeStudy.getRegisteredDate(),
            freeStudy.getViewCount(), 
            freeStudy.getLikeMembers().size());
      }
      System.out.println();
    }
  }
}
