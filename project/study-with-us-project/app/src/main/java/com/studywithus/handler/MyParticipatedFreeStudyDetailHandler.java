package com.studywithus.handler;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class MyParticipatedFreeStudyDetailHandler implements Command {

  HashMap<String, List<Study>> myParticipatedFreeStudyMap;

  public MyParticipatedFreeStudyDetailHandler(HashMap<String, List<Study>> myParticipatedFreeStudyMap) {
    this.myParticipatedFreeStudyMap = myParticipatedFreeStudyMap;
  }

  @Override
  public void execute() {
    /* 해쉬맵의 value값을 myParticipatedFreeStudy에 담음 
     * 전역변수로 둘 경우 App 실행 시 getLoginUser() nullPointer 에러뜸 */
    List<Study> myParticipatedFreeStudy = myParticipatedFreeStudyMap.get(AuthLoginHandler.getLoginUser().getId());
    System.out.println("[마이 페이지 / 내가 참여한 무료 스터디]\n");

    // 내가 참여한 무료 스터디 리스트 출력
    for (Study freeStudy : myParticipatedFreeStudy) {
      System.out.println("스터디 제목:" + freeStudy.getTitle());
      System.out.println("팀장:" + freeStudy.getWriter().getName());
      System.out.println();
    }
    // 내가 참여한 무료 스터디 상세보기
    System.out.println();
    String title = Prompt.inputString("제목: ");

    Study freeStudy = findByName(title, myParticipatedFreeStudy);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("입력하신 제목과 일치하는 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", freeStudy.getTitle());

    if (freeStudy.getArea() != null) {
      System.out.printf("지역: %s\n", freeStudy.getArea());
    }

    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());

  }

  private Study findByName(String title, List<Study> myParticipatedFreeStudyMap) {
    for (Study freeStudy : myParticipatedFreeStudyMap) {
      if (freeStudy.getTitle().equals(title)) {
        return freeStudy;
      }
    }
    return null;
  }
}
