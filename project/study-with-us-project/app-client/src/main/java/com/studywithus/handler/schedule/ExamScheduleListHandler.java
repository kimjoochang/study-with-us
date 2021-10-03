package com.studywithus.handler.schedule;

import java.util.Collection;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;

public class ExamScheduleListHandler implements Command {

  RequestAgent requestAgent;

  public ExamScheduleListHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception{
    System.out.println("[이달의 시험일정 / 조회]\n");

    requestAgent.request("examSchedule.selectList", null);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("이달의 시험일정 조회를 실패하였습니다.");
      return;
    }

    Collection<Schedule> examScheduleList = requestAgent.getObjects(Schedule.class);

    for (Schedule examSchedule : examScheduleList) {
      System.out.printf("[번호 = %d, 제목 = %s, 시험일 = %s]\n", 
          examSchedule.getNo(),
          examSchedule.getTitle(), 
          examSchedule.getStartDate());
    }
  }
}
