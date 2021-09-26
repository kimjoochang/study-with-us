package com.studywithus.handler.study;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;

public class ParticipateFreeStudyListHandler implements Command {

  Study freeStudy;
  List<Study> myParticipatedFreeStudyList;
  HashMap<String, List<Study>> participateFreeStudyMap;

  public ParticipateFreeStudyListHandler(HashMap<String, List<Study>> participateFreeStudyMap, List<Study> myParticipatedFreeStudyList) {
    this.participateFreeStudyMap = participateFreeStudyMap;
    this.myParticipatedFreeStudyList = myParticipatedFreeStudyList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[마이 페이지 / 내가 참여한 무료 스터디]\n");
    /* 해쉬맵의 value값을 myParticipatedFreeStudy에 담음 
     * 전역변수로 둘 경우 App 실행 시 getLoginUser() nullPointer 에러뜸 */
    myParticipatedFreeStudyList = participateFreeStudyMap.get(AuthLogInHandler.getLoginUser().getId());

    if (myParticipatedFreeStudyList.isEmpty()) {
      System.out.println("참여 무료 스터디가 존재하지 않습니다.\n");
      return;
    }

    Date nowDate = new Date(System.currentTimeMillis());

    for (int i = 0; i < myParticipatedFreeStudyList.size(); i++) {
      // 현재 시간 < 시작일
      if (myParticipatedFreeStudyList.get(i).getStartDate().compareTo(nowDate) == 1) {

        // 모집 인원 = 참여 인원
        if (myParticipatedFreeStudyList.get(i).getMembers().size() == myParticipatedFreeStudyList.get(i).getMaxMembers()) {
          System.out.println("<<모집 완료>>");
          list(myParticipatedFreeStudyList);
          System.out.println();
        }

        System.out.println("<<모집 중>>");
        list(myParticipatedFreeStudyList);
        System.out.println();
      }

      // 현재 시간 >= 시작일
      if (myParticipatedFreeStudyList.get(i).getStartDate().compareTo(nowDate) != 1) {
        System.out.println("<<진행 중>>");
        list(myParticipatedFreeStudyList);
        System.out.println();
      }
    }
  }

  // 내가 참여한 무료 스터디 리스트 출력
  private void list(List<Study> myRegisteredFreeStudy) {
    for (Study freeStudy : myRegisteredFreeStudy) {
      System.out.printf("[번호 = %d, 제목 = %s, 팀장 = %s, 온/오프라인 = %s, ", 
          freeStudy.getNo(),
          freeStudy.getTitle(),
          freeStudy.getWriter().getName(),
          freeStudy.getOFFLINE());

      if (freeStudy.getOFFLINE() == "오프라인") {
        System.out.printf("지역 = %s, ", freeStudy.getArea());
      }

      System.out.printf("모집 인원 = %d / %d, 등록일 = %s, 조회수 = %d, 좋아요 = %d]\n",
          freeStudy.getMembers().size(),
          freeStudy.getMaxMembers(),
          freeStudy.getRegisteredDate(),
          freeStudy.getViewCount(),
          freeStudy.getLikeMembers().size());
    }
  }
}
