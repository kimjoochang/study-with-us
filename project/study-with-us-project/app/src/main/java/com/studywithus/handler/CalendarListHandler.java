package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;

public class CalendarListHandler extends AbstractCalendarHandler{
  public CalendarListHandler(List<Calendar> jobsCalendarList, List<Calendar> examCalendarList) {
    super(jobsCalendarList, examCalendarList);
  }

  @Override
  public void execute() {
    System.out.println("[캘린더 / 조회]\n");

    for (Calendar calendar : jobsCalendarList) {
      System.out.printf("%d, %s, %s, %s\n", 
          calendar.getNo(),
          calendar.getTitle(), 
          calendar.getStartDate(),
          calendar.getEndDate());
      System.out.println();
    }

    for (Calendar calendar : examCalendarList) {
      System.out.printf("%d, %s, %s, %s\n", 
          calendar.getNo(),
          calendar.getTitle(), 
          calendar.getExamDate());
      System.out.println();
    }
  }
}
