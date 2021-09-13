package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;

public abstract class AbstractCalendarHandler implements Command {

  protected List<Calendar> calendarList;

  public AbstractCalendarHandler(List<Calendar> calendarList) {
    this.calendarList = calendarList;
  }

  // 캘린더 게시글 번호 조회
  protected Calendar findByNo(int no) {
    for (Calendar calendar: calendarList) {
      if (calendar.getNo() == no) {
        return calendar;
      }
    }
    return null;
  }
}
