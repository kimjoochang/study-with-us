package com.studywithus.handler.calendar;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.Command;

public abstract class AbstractCalendarHandler implements Command {

  protected List<Calendar> calendarList;

  public AbstractCalendarHandler(List<Calendar> calendarList) {
    this.calendarList = calendarList;
  }

  // 캘린더 게시글 번호 검색
  protected Calendar findByNo(int no) {
    for (Calendar calendar: calendarList) {
      if (calendar.getNo() == no) {
        return calendar;
      }
    }
    return null;
  }

  // 캘린더 게시글 제목 검색
  //  protected Calendar findByTitle(String title) {
  //    for (Calendar calendar: calendarList) {
  //      if (calendar.getTitle() == title) {
  //        return calendar;
  //      }
  //    }
  //    return null;
  //  }
}
