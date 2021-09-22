package com.studywithus.handler.calendar;

import java.util.GregorianCalendar;
import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ExamCalendarAddHandler extends AbstractCalendarHandler {

  GregorianCalendar calendar = new GregorianCalendar();

  public ExamCalendarAddHandler(List<Calendar> examCalendarList) {
    super(examCalendarList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("이달의 시험일정 / 등록]\n");

    Calendar examCalendar = new Calendar();

    examCalendar.setNo(Prompt.inputInt("번호를 입력하세요. > "));
    examCalendar.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    examCalendar.setWriter(AuthLogInHandler.getLoginUser());
    examCalendar.setContent(Prompt.inputString("내용을 입력하세요. > "));

    System.out.println("시험일을 입력하세요.");

    // 년
    while (true) {
      examCalendar.setYyyy(Prompt.inputInt("YYYY > "));

      if (examCalendar.getYyyy() < 2021) {
        System.out.println("유효한 연도를 입력하시오.");
        continue;

      } else {
        break;
      }
    }

    // 월
    while (true) {
      examCalendar.setMm(Prompt.inputInt("MM > "));

      if (!(1 <= examCalendar.getMm() && examCalendar.getMm() <= 12)) {
        System.out.println("유효한 월을 입력하시오.");
        continue;

      } else {
        break;
      }
    }

    // 일
    while (true) {
      examCalendar.setDd(Prompt.inputInt("DD > "));

      // 1 ~ 31일
      switch (examCalendar.getMm()) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:

          if (!(examCalendar.getDd() >= 1 && examCalendar.getDd() <= 31)) {
            System.out.println("유효한 일을 입력하시오.");
            continue;

          } else {
            break;
          }
      }

      // 1 ~ 30일
      switch (examCalendar.getMm()) {
        case 4:
        case 6:
        case 9:
        case 11:

          if (!(examCalendar.getDd() >= 1 && examCalendar.getDd() <= 30)) {
            System.out.println("유효한 일을 입력하시오.");
            continue;

          } else {
            break;
          }
      }

      // 1 ~ 28일
      switch (examCalendar.getMm()) {
        case 2:

          if (!(examCalendar.getDd() >= 1 && examCalendar.getDd() <= 28)) {
            System.out.println("유효한 일을 입력하시오.");
            continue;

          } else {
            break;
          }
      }
      break;
    }

    calendarList.add(examCalendar);

    System.out.println();
    System.out.println("이달의 시험일정 등록이 완료되었습니다.\n");
  }
}
