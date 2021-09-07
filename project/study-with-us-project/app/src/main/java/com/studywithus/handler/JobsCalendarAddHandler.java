package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.JobsCalendar;
import com.studywithus.util.Prompt;

public class JobsCalendarAddHandler extends AbstractJobsCalendarHandler {

  public JobsCalendarAddHandler(List<JobsCalendar> jobsCalendarList) {
    super(jobsCalendarList);
  }

  // 이달의 채용공고 생성
  @Override
  public void execute() {
    System.out.println("[이달의 채용공고 / 등록]\n");

    JobsCalendar jobsCalendar= new JobsCalendar();

    jobsCalendar.setNo(Prompt.inputInt("번호? "));
    jobsCalendar.setTitle(Prompt.inputString("제목? "));
    jobsCalendar.setContent(Prompt.inputString("내용? "));
    jobsCalendar.setStartDate(Prompt.inputString("시작일? "));
    jobsCalendar.setEndDate(Prompt.inputString("종료일? "));

    jobsCalendarList.add(jobsCalendar);

    System.out.println();
    System.out.println("이달의 채용공고 등록이 완료되었습니다.\n");
  }
}
