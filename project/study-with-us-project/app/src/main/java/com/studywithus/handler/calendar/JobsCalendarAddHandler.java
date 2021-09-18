package com.studywithus.handler.calendar;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class JobsCalendarAddHandler extends AbstractCalendarHandler {

  public JobsCalendarAddHandler(List<Calendar> jobsCalendarList) {
    super(jobsCalendarList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[이달의 채용공고 / 등록]\n");

    Calendar jobsCalendar= new Calendar();

    jobsCalendar.setNo(Prompt.inputInt("번호: "));
    jobsCalendar.setTitle(Prompt.inputString("제목: "));
    jobsCalendar.setContent(Prompt.inputString("내용: "));
    jobsCalendar.setStartDate(Prompt.inputString("시작일: "));
    jobsCalendar.setEndDate(Prompt.inputString("종료일: "));

    calendarList.add(jobsCalendar);

    System.out.println();
    System.out.println("이달의 채용공고 등록이 완료되었습니다.\n");
  }
}
