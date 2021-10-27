package com.studywithus.handler.calendar;

import java.sql.Date;
import com.studywithus.dao.CalendarDao;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ExamCalendarAddHandler implements Command {

  CalendarDao scheduleDao;

  public ExamCalendarAddHandler(CalendarDao scheduleDao) {
    this.scheduleDao = scheduleDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("이달의 시험일정 / 생성]\n");

    Calendar examCalendar = new Calendar();

    examCalendar.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    examCalendar.setWriter(AuthLogInHandler.getLoginUser());
    examCalendar.setContent(Prompt.inputString("내용을 입력하세요. > "));

    while (true) {
      examCalendar.setStartDate(Prompt.inputDate("시험일을 입력하세요. ex) YYYY-MM-DD > "));

      // 현재 날짜 > 시험일인 경우
      if (new Date(System.currentTimeMillis()).compareTo(examCalendar.getStartDate()) == 1) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    scheduleDao.insert(examCalendar);
  }
}
