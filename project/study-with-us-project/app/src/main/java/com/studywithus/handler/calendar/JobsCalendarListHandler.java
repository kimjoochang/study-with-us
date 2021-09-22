package com.studywithus.handler.calendar;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.CommandRequest;

public class JobsCalendarListHandler extends AbstractCalendarHandler {

  public JobsCalendarListHandler(List<Calendar> jobsCalendarList) {
    super(jobsCalendarList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[이달의 채용공고 / 조회]\n");

    if (calendarList.isEmpty() == true) {
      System.out.println("이달의 채용공고가 존재하지 않습니다.\n");
      return;
    }

    for (Calendar jobsCalendar : calendarList) {
      System.out.printf("[번호 = %d, 제목 = %s, 시작일 = %d-%d-%d, 종료일 = %d-%d-%d]\n",
          jobsCalendar.getNo(),
          jobsCalendar.getTitle(),
          jobsCalendar.getStartYyyy(),
          jobsCalendar.getStartMm(),
          jobsCalendar.getStartDd(),
          jobsCalendar.getYyyy(),
          jobsCalendar.getMm(),
          jobsCalendar.getDd());
    }
    System.out.println();
  }
}
