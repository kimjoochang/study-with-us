package com.studywithus.handler.calendar;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ExamCalendarAddHandler extends AbstractCalendarHandler {

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
    examCalendar.setYyyy(Prompt.inputInt("YYYY > "));
    examCalendar.setMm(Prompt.inputInt("MM > "));
    examCalendar.setDd(Prompt.inputInt("DD > "));

    calendarList.add(examCalendar);

    System.out.println();
    System.out.println("이달의 시험일정 등록이 완료되었습니다.\n");
  }
}
