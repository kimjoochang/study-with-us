package com.studywithus.handler;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class MyRegisteredFreeStudyDetailHandler implements Command {

  HashMap<String, List<Study>> myStudyMap;

  public MyRegisteredFreeStudyDetailHandler(HashMap<String, List<Study>> myStudyMap) {
    this.myStudyMap = myStudyMap;
  }

  @Override
  public void execute() {
    // 해쉬맵의 value값을 myRegisteredFreeStudy에 담음
    List<Study> myRegisteredFreeStudy = myStudyMap.get(AuthLoginHandler.getLoginUser().getId());

    System.out.println("[마이 페이지 / 내가 생성한 무료 스터디]\n");

    for (Study freeStudy : myRegisteredFreeStudy) {
      System.out.println("스터디 제목:" + freeStudy.getTitle());
      System.out.println("등록일:" + freeStudy.getRegisteredDate());
      System.out.println();
    }
    System.out.println();
    String title = Prompt.inputString("제목: ");

    Study freeStudy = findByName(title, myRegisteredFreeStudy);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("입력하신 제목과 일치하는 스터디가 없습니다.\n");
      return;
    }

    System.out.printf("제목: %s\n", freeStudy.getTitle());
    System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

    if (freeStudy.getArea() != null) {
      System.out.printf("지역: %s\n", freeStudy.getArea());
    }

    System.out.printf("설명: %s\n", freeStudy.getContent());
    System.out.printf("규칙: %s\n", freeStudy.getRule());
    System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());
    System.out.printf("조회수: %d\n", freeStudy.getViewCount());

  }

  private Study findByName(String title, List<Study> myRegisteredFreeStudy) {
    for (Study freeStudy : myRegisteredFreeStudy) {
      if (freeStudy.getTitle().equals(title)) {
        return freeStudy;
      }
    }
    return null;
  }
}
