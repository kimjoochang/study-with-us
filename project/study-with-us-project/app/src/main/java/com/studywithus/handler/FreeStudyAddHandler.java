package com.studywithus.handler;

import java.sql.Date;
import java.util.List;
import com.studywithus.domain.FreeStudy;
import com.studywithus.util.Prompt;

public class FreeStudyAddHandler extends AbstractFreeStudyHandler {

  public FreeStudyAddHandler(List<FreeStudy> freeStudyList) {
    super(freeStudyList);

    FreeStudy testUser = new FreeStudy();
    testUser.setNo(1);
    testUser.setWriter("팀장 1");
    testUser.setOnOffLine(2);
    testUser.setArea("서울시 강남구");
    testUser.setTitle("[자바] 자료구조 스터디 1");
    testUser.setExplanation("Array / Linked List / Stack");
    testUser.setRule("규칙 1");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    freeStudyList.add(testUser);

    testUser = new FreeStudy();
    testUser.setNo(2);
    testUser.setWriter("팀장 2");
    testUser.setOnOffLine(1);
    testUser.setTitle("[자바] 자료구조 스터디 2");
    testUser.setExplanation("Array / Linked List / Stack");
    testUser.setRule("규칙 2");
    testUser.setRegisteredDate(new Date(System.currentTimeMillis()));

    freeStudyList.add(testUser);
  }

  // 무료 스터디 생성
  public void execute() {
    System.out.println("[무료 스터디 / 생성]\n");

    FreeStudy freeStudy = new FreeStudy();

    freeStudy.setNo(Prompt.inputInt("번호? "));
    freeStudy.setWriter(Prompt.inputString("팀장? "));

    System.out.println("온/오프라인?");
    System.out.println("1. 온라인");
    System.out.println("2. 오프라인");
    freeStudy.setOnOffLine(Prompt.inputInt("> "));

    if (freeStudy.getOnOffLine() == 2) {
      freeStudy.setArea(Prompt.inputString("지역? "));

    } else {
      freeStudy.setArea(null);
    }

    freeStudy.setTitle(Prompt.inputString("제목? "));
    freeStudy.setExplanation(Prompt.inputString("설명? "));
    freeStudy.setRule(Prompt.inputString("규칙? "));
    freeStudy.setRegisteredDate(new Date(System.currentTimeMillis()));

    freeStudyList.add(freeStudy);

    System.out.println();
    System.out.println("무료 스터디 등록이 완료되었습니다.\n");
  }
}
