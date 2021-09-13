package com.studywithus.handler;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class FreeStudyAddHandler extends AbstractStudyHandler {

  HashMap<String, List<Study>> myStudyMap; // 팀장과 팀장 본인이 생성한 스터디 연결할 해쉬맵

  public FreeStudyAddHandler(List<Study> freeStudyList, HashMap<String, List<Study>> myStudyMap) {
    super(freeStudyList);
    this.myStudyMap = myStudyMap;
  }

  @Override
  public void execute() {
    System.out.println("[무료 스터디 / 등록]\n");

    Study freeStudy = new Study();

    freeStudy.setWriter(AuthLoginHandler.getLoginUser());

    freeStudy.setNo(Prompt.inputInt("번호: "));
    System.out.println("온/오프라인");
    System.out.println("1. 온라인");
    System.out.println("2. 오프라인");
    freeStudy.setOnOffLine(Prompt.inputInt("> "));

    if (freeStudy.getOnOffLine() == 2) {
      freeStudy.setArea(Prompt.inputString("지역: "));

    } else {
      freeStudy.setArea(null);
    }

    freeStudy.setTitle(Prompt.inputString("제목: "));
    freeStudy.setContent(Prompt.inputString("설명: "));
    freeStudy.setRule(Prompt.inputString("규칙: "));
    freeStudy.setRegisteredDate(new Date(System.currentTimeMillis()));

    studyList.add(freeStudy);

    List<Study> myRegisteredFreeStudy; // 해쉬맵에 객체 담기 위한 임시 변수


    /* 해쉬맵에 key값으로 로그인한 회원 id , value값으로 팀장 본인이 생성한 스터디 리스트 
     * 만약, 해당 아이디가 생성리스트를 갖고 있다면 기존 생성리스트에 스터디 추가 */
    if (myStudyMap.containsKey(AuthLoginHandler.getLoginUser().getId())) {
      myRegisteredFreeStudy = myStudyMap.get(AuthLoginHandler.getLoginUser().getId());
      myRegisteredFreeStudy.add(freeStudy);
      myStudyMap.put(AuthLoginHandler.getLoginUser().getId(), myRegisteredFreeStudy);
      // 생성리스트가 없는 회원이라면 새로운 생성리스트에 스터디 추가
    } else {
      myRegisteredFreeStudy = new ArrayList<>();
      myRegisteredFreeStudy.add(freeStudy);
      myStudyMap.put(AuthLoginHandler.getLoginUser().getId(), myRegisteredFreeStudy);
    }

    AuthLoginHandler.userAccessLevel |= Menu.ACCESS_LEADER;

    System.out.println();
    System.out.println("무료 스터디 등록이 완료되었습니다.\n");
  }
}
