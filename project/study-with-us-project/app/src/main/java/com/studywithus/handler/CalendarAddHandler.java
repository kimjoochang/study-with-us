package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.util.Prompt;

public class CalendarAddHandler extends AbstractCalendarHandler{

  public CalendarAddHandler(List<Calendar> jobsCalendarList, List<Calendar> examCalendarList) {
    super(jobsCalendarList, examCalendarList);
  }

  // 캘린더 생성
  @Override
  public void execute() {
    System.out.println("[캘린더 / 등록]\n");

    Calendar calendar= new Calendar();

    String   = Prompt.inputString("생성할 캘린더? (채용공고 / 시험일정)");

    calendar.setKind(kind);
    calendar.setTitle(Prompt.inputString("제목? "));
    calendar.setContent(Prompt.inputString("내용? "));

    if (kind.equals("채용공고")) {
      calendar.setStartDate(Prompt.inputString("시작일? "));
      calendar.setEndDate(Prompt.inputString("종료일? "));
      jobsCalendarList.add(calendar);
    } else {
      calendar.setExamDate(Prompt.inputString("시험일? "));
      examCalendarList.add(calendar);
    }


    System.out.println();
    System.out.println("캘린더 등록이 완료되었습니다.\n");
  }
}
