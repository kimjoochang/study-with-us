package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.util.Prompt;

public class CalendarAddHandler extends AbstractCalendarHandler{

  public CalendarAddHandler(List<Calendar> calendarList) {
    super(calendarList);
  }

  // 캘린더 생성
  @Override
  public void execute() {
    System.out.println("[캘린더 / 등록]\n");

    Calendar calendar= new Calendar();

    //    calendar.setNo(Prompt.inputInt("번호? "));
    calendar.setTitle(Prompt.inputString("제목? "));
    calendar.setContent(Prompt.inputString("내용? "));
    calendar.setStartDate(Prompt.inputString("시작일? "));
    calendar.setEndDate(Prompt.inputString("종료일? "));

    calendarList.add(calendar);

    System.out.println();
    System.out.println("캘린더 등록이 완료되었습니다.\n");
  }
}
