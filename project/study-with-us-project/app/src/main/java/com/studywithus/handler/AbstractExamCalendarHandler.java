package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;

public abstract class AbstractExamCalendarHandler implements Command {

  protected List<Calendar> examCalendarList;

  public AbstractExamCalendarHandler(List<Calendar> examCalendarList) {
    this.examCalendarList = examCalendarList;
  }

  // 이달의 시험일정 번호 조회
  protected Calendar findByNo(int no) {
    for (Calendar examCalendar : examCalendarList) {
      if (examCalendar.getNo() == no) {
        return examCalendar;
      }
    }
    return null;
  }
}
