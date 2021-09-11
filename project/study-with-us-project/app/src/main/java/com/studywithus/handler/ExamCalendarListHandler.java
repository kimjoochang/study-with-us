package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;

public class ExamCalendarListHandler extends AbstractExamCalendarHandler {

  public ExamCalendarListHandler(List<Calendar> examCalendarList) {
    super(examCalendarList);
  }

  @Override
  public void execute() {
    System.out.println("[이달의 시험일정 / 조회]\n");

    for (Calendar calendar : examCalendarList) {
      System.out.printf("%d, %s, %s, %s\n", 
          calendar.getNo(),
          calendar.getTitle(), 
          calendar.getExamDate());

      System.out.println();
    }
  }
}
