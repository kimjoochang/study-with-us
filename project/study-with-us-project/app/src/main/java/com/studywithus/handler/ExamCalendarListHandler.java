package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;

public class ExamCalendarListHandler extends AbstractCalendarHandler {

  public ExamCalendarListHandler(List<Calendar> examCalendarList) {
    super(examCalendarList);
  }

  @Override
  public void execute() {
    System.out.println("[이달의 시험일정 / 조회]\n");

    if (calendarList != null) {

      for (Calendar calendar : calendarList) {
        System.out.printf("[번호 = %d, 제목 = %s, 시험일 = %s]\n", 
            calendar.getNo(),
            calendar.getTitle(), 
            calendar.getExamDate());
        System.out.println();
        return;
      }
    }

    System.out.println();
    System.out.println("이달의 시험일정 게시글이 존재하지 않습니다.\n");
  }
}


