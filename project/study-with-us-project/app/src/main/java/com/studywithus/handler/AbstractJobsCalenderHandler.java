package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.JobsCalender;

public abstract class AbstractJobsCalenderHandler implements Command {

  protected List<JobsCalender> jobsCalenderList;

  public AbstractJobsCalenderHandler(List<JobsCalender> jobsCalenderList) {
    this.jobsCalenderList = jobsCalenderList;
  }

  // 이달의 채용공고 번호 조회
  protected JobsCalender findByNo(int no) {
    for (JobsCalender jobsCalender : jobsCalenderList) {
      if (jobsCalender.getNo() == no) {
        return jobsCalender;
      }
    }
    return null;
  }
}
