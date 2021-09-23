package com.studywithus.handler.schedule;

import java.util.List;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.CommandRequest;

public class JobsScheduleListHandler extends AbstractScheduleHandler {

  public JobsScheduleListHandler(List<Schedule> jobsCalendarList) {
    super(jobsCalendarList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[이달의 채용공고 / 조회]\n");

    if (scheduleList.isEmpty() == true) {
      System.out.println("이달의 채용공고가 존재하지 않습니다.");
      return;
    }

    for (Schedule jobsCalendar : scheduleList) {
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
  }
}
