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

public class ChargeStudyAddHandler extends AbstractStudyHandler {

  // 각 멘토의 생성 유료 스터디 리스트
  HashMap<String, List<Study>> registerChargeStudyMap;

  public ChargeStudyAddHandler(List<Study> chargeStudyList, 
      HashMap<String, List<Study>> registerChargeStudyMap) {
    super(chargeStudyList);
    this.registerChargeStudyMap = registerChargeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[유료 스터디 / 생성]\n");

    Study chargeStudy = new Study();


    if (!studyList.isEmpty()) {
      Study lastElement = studyList.get(studyList.size() - 1);
      chargeStudy.setNo(lastElement.getNo() + 1);
    } else {
      chargeStudy.setNo(1);
    }

    chargeStudy.setWriter(AuthLogInHandler.getLoginUser());
    chargeStudy.setArea(Prompt.inputString("지역: "));
    chargeStudy.setTitle(Prompt.inputString("스터디 제목: "));
    chargeStudy.setContent(Prompt.inputString("스터디 설명: "));
    chargeStudy.setMaxMembers(Prompt.inputInt("모집 인원: "));
    chargeStudy.setPrice(Prompt.inputInt("가격: " ));

    while (true) {
      try {
        chargeStudy.setStartDate(Prompt.inputDate("시작일을 입력하세요. ex) YYYY-MM-DD > "));

      } catch (IllegalArgumentException e) {
        System.out.println("다시 입력하세요.\n");
        continue;
      }

      // 현재 날짜 > 시작일인 경우
      /*if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getStartDate()) == 1) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }*/
      break;
    }

    while (true) {
      try {
        chargeStudy.setEndDate(Prompt.inputDate("종료일을 입력하세요. ex) YYYY-MM-DD > "));

      } catch (IllegalArgumentException e) {
        System.out.println("다시 입력하세요.\n");
        continue;
      }

      /*if (new Date(System.currentTimeMillis()).compareTo(chargeStudy.getEndDate()) == 1) {
        System.out.println("다시 입력하세요.\n");
        continue;

        // 시작일 < 종료일이 아닌 경우
      }else if (chargeStudy.getEndDate().compareTo(chargeStudy.getStartDate()) != 1) {
        System.out.println("종료일은 시작일 이후로 설정하세요.\n");
        continue;

      } else {
        break;
      }*/
      break;
    }

    chargeStudy.setRegisteredDate(new Date(System.currentTimeMillis()));

    studyList.add(chargeStudy);

    List<Study> registerChargeStudyList; // 해쉬맵에 객체 담기 위한 임시 변수


    // 생성 유료 스터디에 해당 아이디 존재 O
    if (registerChargeStudyMap.containsKey(AuthLogInHandler.getLoginUser().getId())) {
      // 생성 유료 스터디에 아이디 호출 -> 생성 유료 스터디 리스트에 대입
      registerChargeStudyList = registerChargeStudyMap.get(AuthLogInHandler.getLoginUser().getId());

      // 생성 유료 스터디 리스트에 유료 스터디 추가
      registerChargeStudyList.add(chargeStudy);
      // 생성 유료 스터디에 아이디 추가
      registerChargeStudyMap.put(AuthLogInHandler.getLoginUser().getId(), registerChargeStudyList);

      // 생성 유료 스터디에 해당 아이디 존재 X
    } else {
      // 새로운 생성 유료 스터디 리스트 생성
      registerChargeStudyList = new ArrayList<>();

      // 생성 유료 스터디 리스트에 유료 스터디 추가
      registerChargeStudyList.add(chargeStudy);
      // 생성 유료 스터디에 아이디 추가
      registerChargeStudyMap.put(AuthLogInHandler.getLoginUser().getId(), registerChargeStudyList);
    }

    AuthLogInHandler.userAccessLevel |= Menu.ACCESS_MENTOR;

    System.out.println();
    System.out.println("유료스터디 등록이 완료되었습니다.\n");
  }

  private String setDate(Study chargeStudy) {
    Calendar cal = Calendar.getInstance();

    String year;
    String month;
    String date;
    String fullDate; // 입력받은 연도, 월, 일을 합친 변수
    String temp = ""; // 리턴하기 위한 임시 변수
    String[] arr = new String[2]; // 시작일을 split으로 자르고 담을 배열
    int type = 0; // 시작일과 종료일 구분하기 위한 변수

    if (chargeStudy.getStartDate() != null) {
      type = 1; // type이 1이라면 종료일
      //			arr = chargeStudy.getStartDate().split("-");
    }

    // 연도
    while (true) {
      year = Prompt.inputString("YYYY > ");

      if (year.length() != 4) {
        System.out.println("유효한 연도를 입력하시오.\n");
        continue;
      }

      // 종료일 입력받을 때
      // 시작일 년도보다 빠르면 재입력 받도록 함
      if (type == 1) {
        if (Integer.parseInt(year) < Integer.parseInt(arr[0])) {
          System.out.println("유효한 연도를 입력하시오.\n");
          continue;
        }
      }

      // 현재 연도와 비교해서 현재년도와 다음년도까지만 유효한 값으로 허용
      if (Integer.parseInt(year) < cal.get(Calendar.YEAR)  
          || Integer.parseInt(year) > cal.get(Calendar.YEAR) + 1) {
        System.out.println("유효한 연도를 입력하시오.\n");
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
          System.out.println("유효한 월을 입력하시오.\n");
          continue;
        }
      }

      // 현재 년도와 입력 년도가 같다면,
      if (Integer.parseInt(year) == cal.get(Calendar.YEAR)) {
        // 현재 월과 12까지만 유효한 값으로 허용
        // cal.get(Calendar.MONTH)은 1월이 0이므로 +1 해줌
        if (Integer.parseInt(month) < cal.get(Calendar.MONTH) + 1  || Integer.parseInt(month) > 12) {
          System.out.println("유효한 월을 입력하시오.\n");
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
          System.out.println("유효한 월을 입력하시오.\n");
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
          System.out.println("유효한 일을 입력하시오.\n");
          continue;
        }
      }

      // 입력 월이 현재 월과 같을 경우, 
      if (Integer.parseInt(month) == cal.get(Calendar.MONTH) + 1) {
        // 현재 날짜보다 크고 현재 월의 말일보다 작은 값만 유효한 값으로 허용
        if (Integer.parseInt(date) < cal.get(Calendar.DATE) + 1
            || Integer.parseInt(date) > cal.getActualMaximum(Calendar.DAY_OF_MONTH)) {
          System.out.println("유효한 일을 입력하시오.\n");
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
          System.out.println("유효한 일을 입력하시오\n");
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
