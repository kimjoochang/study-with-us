package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.JobsCalendar;

public abstract class AbstractJobsCalendarHandler implements Command {

  protected List<JobsCalendar> jobsCalendarList;

  public AbstractJobsCalendarHandler(List<JobsCalendar> jobsCalendarList) {
    this.jobsCalendarList = jobsCalendarList;
  }

  // 이달의 채용공고 번호 조회
  protected JobsCalendar findByNo(int no) {
    for (JobsCalendar jobsCalendar: jobsCalendarList) {
      if (jobsCalendar.getNo() == no) {
        return jobsCalendar;
      }
    }
    return null;
  }
}
