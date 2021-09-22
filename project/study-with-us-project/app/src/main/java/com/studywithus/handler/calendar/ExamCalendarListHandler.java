package com.studywithus.handler.calendar;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.CommandRequest;

public class ExamCalendarListHandler extends AbstractCalendarHandler {

  public ExamCalendarListHandler(List<Calendar> examCalendarList) {
    super(examCalendarList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[이달의 시험일정 / 조회]\n");

    if (calendarList.isEmpty() == true) {
      System.out.println("이달의 시험일정이 존재하지 않습니다.\n");
      return;
    }

    for (Calendar examCalendar : calendarList) {
      System.out.printf("[번호 = %d, 제목 = %s, 시험일 = %d-%d-%d]\n",
          examCalendar.getNo(),
          examCalendar.getTitle(),
          examCalendar.getYyyy(),
          examCalendar.getMm(),
          examCalendar.getDd());
    }
    System.out.println();
  }
}
