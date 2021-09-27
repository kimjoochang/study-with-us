package com.studywithus.handler.schedule;

import java.util.List;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.CommandRequest;

public class ExamScheduleListHandler extends AbstractScheduleHandler {

  public ExamScheduleListHandler(List<Schedule> examScheduleList) {
    super(examScheduleList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[이달의 시험일정 / 조회]\n");

    if (scheduleList.isEmpty() == true) {
      System.out.println("이달의 시험일정이 존재하지 않습니다.");
      return;
    }

    for (Schedule examSchedule : scheduleList) {
      System.out.printf("[번호 = %d, 제목 = %s, 시험일 = %s]\n", examSchedule.getNo(),
          examSchedule.getTitle(), examSchedule.getStartDate());
    }
  }
}
