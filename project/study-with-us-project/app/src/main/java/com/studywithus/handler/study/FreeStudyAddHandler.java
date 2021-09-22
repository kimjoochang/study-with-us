package com.studywithus.handler.study;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Study;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class FreeStudyAddHandler extends AbstractStudyHandler {

  HashMap<String, List<Study>> registerFreeStudyMap; // 팀장과 팀장 본인이 생성한 스터디 연결할 해쉬맵

  public FreeStudyAddHandler(List<Study> freeStudyList, HashMap<String, List<Study>> registerFreeStudyMap) {
    super(freeStudyList);
    this.registerFreeStudyMap = registerFreeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[무료 스터디 / 등록]\n");

    Study freeStudy = new Study();

    freeStudy.setWriter(AuthLogInHandler.getLoginUser());

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
    freeStudy.setMaxMembers(Prompt.inputInt("모집 인원: "));
    System.out.println("시작일을 입력하세요.");
    freeStudy.setStartDate(setDate(freeStudy));
    System.out.println("종료일을 입력하세요.");
    freeStudy.setEndDate(setDate(freeStudy));
    freeStudy.setRegisteredDate(new Date(System.currentTimeMillis()));

    studyList.add(freeStudy);

    List<Study> registerFreeStudyList; // 해쉬맵에 객체 담기 위한 임시 변수


    /* 해쉬맵에 key값으로 로그인한 회원 id , value값으로 팀장 본인이 생성한 스터디 리스트 
     * 만약, 해당 아이디가 생성리스트를 갖고 있다면 기존 생성리스트에 스터디 추가 */
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
    System.out.println("무료 스터디 등록이 완료되었습니다.\n");
  }

  private String setDate(Study freeStudy) {
    Calendar cal = Calendar.getInstance();

    String year;
    String month;
    String date;
    // 입력받은 연도, 월, 일을 합친 변수
    String fullDate;
    // 리턴하기 위한 임시 변수
    String temp = "";
    String[] arr = new String[2];
    int type = 0; // 시작일과 종료일 구분하기 위한 변수

    if (freeStudy.getStartDate() != null) {
      type = 1; // type이 1이라면 종료일
      arr = freeStudy.getStartDate().split("-");
    }

    // 연도
    while (true) {
      year = Prompt.inputString("YYYY > ");

      // 종료일 입력받을 때
      // 시작일 년도보다 빠르면 재입력 받도록 함
      if (type == 1) {
        if (Integer.parseInt(year) < Integer.parseInt(arr[0])) {
          System.out.println("스터디 시작일과 비교했을 때 유효한 값이 아닙니다.");
          continue;
        }
      }

      // 현재 연도와 비교해서 현재년도와 다음년도까지만 유효한 값으로 허용
      if (Integer.parseInt(year) < cal.get(Calendar.YEAR)  
          || Integer.parseInt(year) > cal.get(Calendar.YEAR) + 1) {
        System.out.println("유효한 연도를 입력하시오.");
        continue;

      } else {
        break;
      }
    }

    // 월
    while (true) {
      month = Prompt.inputString("MM > ");

      if(month.length() == 1) {
        month = "0" + month;
      }

      // 종료일 입력받을 때
      // 시작일 년도보다 빠르면 재입력 받도록 함
      if (type == 1) {
        if (Integer.parseInt(year + month) < Integer.parseInt(arr[0] + arr[1])) {
          System.out.println("스터디 시작일과 비교했을 때 유효한 값이 아닙니다.");
          continue;
        }
      }

      // 현재 년도와 입력 년도가 같다면,
      if (Integer.parseInt(year) == cal.get(Calendar.YEAR)) {
        // 현재 월과 12까지만 유효한 값으로 허용
        // cal.get(Calendar.MONTH)은 1월이 0이므로 +1 해줌
        if (Integer.parseInt(month) < cal.get(Calendar.MONTH) + 1  || Integer.parseInt(month) > 12) {
          System.out.println("유효한 월을 입력하시오");
          continue;

        } else {
          break;
        }
        // 현재 년도와 입력 년도가 다르다면,
      } else {
        // 현재 년도와 다르므로 입력 년도로 세팅
        cal.set(Calendar.YEAR, Integer.parseInt(year));
        // 1월부터 12월까지를 유효한 값으로 허용
        if (Integer.parseInt(month) < 1 || Integer.parseInt(month) > 12) {
          System.out.println("유효한 월을 입력하시오");
          continue;
        } else {
          //          cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
          break;
        }
      }
    }

    // 일
    while (true) {
      date = Prompt.inputString("DD > ");

      if(date.length() == 1) {
        date = "0" + date;
      }

      if (type == 1) {
        if (Integer.parseInt(year + month + date) < Integer.parseInt(arr[0] + arr[1] + arr[2])) {
          System.out.println("스터디 시작일과 비교했을 때 유효한 값이 아닙니다.");
          continue;
        }
      }

      // 입력 월이 현재 월과 같을 경우, 
      if (Integer.parseInt(month) == cal.get(Calendar.MONTH) + 1) {
        // 현재 날짜보다 크고 현재 월의 말일보다 작은 값만 유효한 값으로 허용
        if (Integer.parseInt(date) < cal.get(Calendar.DATE) + 1
            || Integer.parseInt(date) > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
          System.out.println("유효한 일을 입력하시오");
          continue;
        } else {
          break;
        }
        // 입력 월이 현재 월과 다를 경우, 
      } else {
        // 현재 월과 다르므로 입력 월로 세팅
        cal.set(Calendar.MONTH, Integer.parseInt(month) - 1);
        // 1보다 크고 입력 월의 말일보다 작은 값만 유효한 값으로 허용
        if ((Integer.parseInt(date) > cal.getActualMaximum(Calendar.DAY_OF_MONTH)
            || Integer.parseInt(date) < 1)) {
          System.out.println("유효한 일을 입력하시오");
          continue;
        } else {
          break;
        }
      }
    }

    try {
      fullDate = year + month + date;
      // 위에서 입력받은 yy , mm ,dd 를 Date 타입의 yyyyMMdd 형식으로 형 변환
      SimpleDateFormat beforeFormat = new SimpleDateFormat("yyyymmdd");
      java.util.Date formatDate = beforeFormat.parse(fullDate);

      // Date 형식으로 변환한 날짜를 다시 String 타입의 yyyy-mm-dd 형식으로 형 변환
      SimpleDateFormat afterFormat = new SimpleDateFormat("yyyy-mm-dd");
      temp = afterFormat.format(formatDate);
    } catch (ParseException e) {
      e.printStackTrace();
    }
    return temp;
  }
}

