package com.studywithus.handler.schedule;

import java.util.Collection;
import com.studywithus.dao.ScheduleDao;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class JobsScheduleListHandler implements Command {

  ScheduleDao scheduleDao;

  public JobsScheduleListHandler(ScheduleDao scheduleDao) {
    this.scheduleDao = scheduleDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 채용공고 / 조회]\n");

    int no = (int) request.getAttribute("scheduleNo");

    Schedule jobsSchedule = scheduleDao.findByNo(no);

    if (jobsSchedule == null) {
      System.out.println("이달의 시험일정 조회를 실패하였습니다.");
      return;
    }

    Collection<Schedule> scheduleList = scheduleDao.findAll();

    for (Schedule jobsCalendar : scheduleList) {
      System.out.printf("[번호 = %d, 제목 = %s, 시작일 = %s, 종료일 = %s]\n", jobsCalendar.getNo(),
          jobsCalendar.getTitle(), jobsCalendar.getStartDate(), jobsCalendar.getEndDate());
    }
  }
}
