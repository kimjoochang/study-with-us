package com.studywithus.handler.schedule;

import java.util.Collection;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;

public class JobsScheduleListHandler implements Command {

  RequestAgent requestAgent;

  public JobsScheduleListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 채용공고 / 조회]\n");

    requestAgent.request("jobsSchedule.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("이달의 시험일정 조회를 실패하였습니다.");
      return;
    }

    Collection<Schedule> scheduleList = requestAgent.getObjects(Schedule.class);

    if (scheduleList.isEmpty()) {
      System.out.println("이달의 채용공고가 없습니다.");
    }

    for (Schedule jobsCalendar : scheduleList) {
      System.out.printf("[번호 = %d, 제목 = %s, 시작일 = %s, 종료일 = %s]\n", jobsCalendar.getNo(),
          jobsCalendar.getTitle(), jobsCalendar.getStartDate(), jobsCalendar.getEndDate());
    }
  }
}
