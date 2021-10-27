package com.studywithus.handler.calendar;

import java.util.Collection;
import com.studywithus.dao.CalendarDao;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class ExamCalendarListHandler implements Command {

  CalendarDao scheduleDao;

  public ExamCalendarListHandler(CalendarDao scheduleDao) {
    this.scheduleDao = scheduleDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 시험일정 / 조회]\n");

    Collection<Calendar> scheduleList = scheduleDao.findAll();

    if (scheduleList.isEmpty()) {
      System.out.println("이달의 시험일정이 없습니다.");
    }

    for (Calendar examSchedule : scheduleList) {
      System.out.printf("[번호 = %d, 제목 = %s, 시험일 = %s]\n", 
          examSchedule.getNo(),
          examSchedule.getTitle(), 
          examSchedule.getStartDate());
    }
  }
}
