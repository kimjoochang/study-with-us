package com.studywithus.handler;

import com.studywithus.domain.Calender;
import com.studywithus.util.Prompt;

public class AbstractJobsCalenderHandler {

  protected List<JobsCalender> jobsCalenderHandlerList;
  
  public AbstractJobsCalenderHandler(List<JobsCalender> jobsCalenderHandlerList) {
    super(jobsCalenderHandlerList);
  }

  // 이달의 채용공고 번호 조회
  private Calender findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.calenders[i].getNo() == no) {
        return this.calenders[i];
      }
    }
    return null;
  }
}
