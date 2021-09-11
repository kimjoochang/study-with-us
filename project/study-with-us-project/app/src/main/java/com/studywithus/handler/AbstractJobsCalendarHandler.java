package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;

public abstract class AbstractJobsCalendarHandler implements Command {

  protected List<Calendar> jobsCalendarList;

  public AbstractJobsCalendarHandler(List<Calendar> jobsCalendarList) {
    this.jobsCalendarList = jobsCalendarList;
  }

  // 이달의 채용공고 번호 조회
  protected Calendar findByNo(int no) {
    for (Calendar jobsCalendar: jobsCalendarList) {
      if (jobsCalendar.getNo() == no) {
        return jobsCalendar;
      }
    }
    return null;
  }
}
