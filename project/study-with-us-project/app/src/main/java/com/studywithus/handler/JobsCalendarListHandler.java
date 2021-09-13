package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;

public class JobsCalendarListHandler extends AbstractCalendarHandler {

  public JobsCalendarListHandler(List<Calendar> jobsCalendarList) {
    super(jobsCalendarList);
  }

  @Override
  public void execute() {
    System.out.println("[이달의 채용공고 / 조회]\n");

    for (Calendar calendar : calendarList) {
      System.out.printf("[번호 = %d, 제목 = %s, 시작일 = %s, 종료일 = %s]\n", 
          calendar.getNo(),
          calendar.getTitle(), 
          calendar.getStartDate(),
          calendar.getEndDate());

      System.out.println();
    }
  }
}
