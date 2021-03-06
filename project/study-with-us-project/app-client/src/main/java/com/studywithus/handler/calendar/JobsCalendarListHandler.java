package com.studywithus.handler.calendar;

import java.util.Collection;
import com.studywithus.dao.CalendarDao;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class JobsCalendarListHandler implements Command {

  CalendarDao scheduleDao;

  public JobsCalendarListHandler(CalendarDao scheduleDao) {
    this.scheduleDao = scheduleDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 채용공고 / 조회]\n");

    //int no = (int) request.getAttribute("scheduleNo");
    //Schedule jobsSchedule = scheduleDao.findByNo(no);

    Collection<Calendar> scheduleList = scheduleDao.findAll();

    if (scheduleList.isEmpty()) {
      System.out.println("이달의 시험일정 조회를 실패하였습니다.");
      return;
    }

    for (Calendar jobsCalendar : scheduleList) {
      System.out.printf("[번호 = %d, 제목 = %s, 시작일 = %s, 종료일 = %s]\n", jobsCalendar.getNo(),
          jobsCalendar.getTitle(), jobsCalendar.getStartDate(), jobsCalendar.getEndDate());
    }
  }
}
