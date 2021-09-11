package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;

public abstract class AbstractCalendarHandler implements Command {

  protected List<Calendar> jobsCalendarList;
  protected List<Calendar> examCalendarList;

  public AbstractCalendarHandler(List<Calendar> jobsCalendarList, List<Calendar> examCalendarList) {
    this.jobsCalendarList = jobsCalendarList;
    this.examCalendarList = examCalendarList;
  }

  // 캘린더 번호 조회
  protected Calendar findByNo(int no) {
    for (Calendar calendar: calendarList) {
      if (calendar.getNo() == no) {
        return calendar;
      }
    }
    return null;
  }
}
