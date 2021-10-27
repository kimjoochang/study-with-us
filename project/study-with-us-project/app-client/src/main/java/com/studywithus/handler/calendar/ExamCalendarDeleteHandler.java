package com.studywithus.handler.calendar;

import com.studywithus.dao.CalendarDao;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ExamCalendarDeleteHandler implements Command {

  CalendarDao scheduleDao;

  public ExamCalendarDeleteHandler(CalendarDao scheduleDao) {
    this.scheduleDao = scheduleDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 시험일정 / 삭제]\n");

    int no = (int) request.getAttribute("scheduleNo");

    Calendar examSchedule = scheduleDao.findByNo(no);

    if (examSchedule == null) {
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("시험일정 삭제를 취소하였습니다.");
      return;
    }

    scheduleDao.delete(no);

    System.out.println();
    System.out.println("시험일정을 삭제하였습니다.");
  }
}
