package com.studywithus.handler.study;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class FreeStudyAddHandler extends AbstractStudyHandler {

  HashMap<String, List<Study>> registerFreeStudyMap; // 팀장과 팀장 본인이 생성한 스터디 연결할 해쉬맵

  public FreeStudyAddHandler(List<Study> freeStudyList,
      HashMap<String, List<Study>> registerFreeStudyMap) {
    super(freeStudyList);
    this.registerFreeStudyMap = registerFreeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 / 등록]\n");

    Study freeStudy = new Study();

    freeStudy.setNo(studyList.size() + 1);
    System.out.println("온/오프라인을 선택하세요.");
    System.out.println("1. 온라인");
    System.out.println("2. 오프라인");
    freeStudy.setOnOffLine(Prompt.inputInt("> "));

    if (freeStudy.getOnOffLine() == 2) {
      freeStudy.setArea(Prompt.inputString("지역을 입력하세요. > "));

    } else {
      freeStudy.setArea(null);
    }

    freeStudy.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    freeStudy.setWriter(AuthLogInHandler.getLoginUser());
    freeStudy.setContent(Prompt.inputString("설명을 입력하세요. > "));
    freeStudy.setRule(Prompt.inputString("규칙을 입력하세요. > "));
    freeStudy.setMaxMembers(Prompt.inputInt("모집 인원을 입력하세요. > "));
    freeStudy.setStartDate(Prompt.inputDate("시작일을 입력하세요. ex) YYYY-MM-DD > "));

    while (true) {
      freeStudy.setEndDate(Prompt.inputDate("종료일을 입력하세요. ex) YYYY-MM-DD > "));

      // 시작일 < 종료일이 아닌 경우
      if (freeStudy.getEndDate().compareTo(freeStudy.getStartDate()) != 1) {
        System.out.println("종료일은 시작일 이후로 설정하세요.\n");
        continue;

        // 현재 날짜 > 시험일인 경우
      } else if (new Date(System.currentTimeMillis()).compareTo(freeStudy.getStartDate()) == 1) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    freeStudy.setRegisteredDate(new Date(System.currentTimeMillis()));

    studyList.add(freeStudy);

    List<Study> registerFreeStudyList; // 해쉬맵에 객체 담기 위한 임시 변수

    // 해쉬맵에 key값으로 로그인한 회원 id , value값으로 팀장 본인이 생성한 스터디 리스트 만약, 해당 아이디가 생성리스트를 갖고 있다면 기존 생성리스트에 스터디
    // 추가
    if (registerFreeStudyMap.containsKey(AuthLogInHandler.getLoginUser().getId())) {
      registerFreeStudyList = registerFreeStudyMap.get(AuthLogInHandler.getLoginUser().getId());

      registerFreeStudyList.add(freeStudy);
      registerFreeStudyMap.put(AuthLogInHandler.getLoginUser().getId(), registerFreeStudyList);

      // 생성리스트가 없는 회원이라면 새로운 생성리스트에 스터디 추가
    } else {
      registerFreeStudyList = new ArrayList<>();

      registerFreeStudyList.add(freeStudy);
      registerFreeStudyMap.put(AuthLogInHandler.getLoginUser().getId(), registerFreeStudyList);
    }

    AuthLogInHandler.userAccessLevel |= Menu.ACCESS_LEADER;

    System.out.println();
    System.out.println("무료 스터디 등록이 완료되었습니다.");
  }
}
