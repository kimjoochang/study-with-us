package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.JobsCalender;
import com.studywithus.util.Prompt;

public class JobsCalenderAddHandler extends AbstractJobsCalenderHandler {

  public JobsCalenderAddHandler(List<JobsCalender> jobsCalenderList) {
    super(jobsCalenderList);
  }

  // 이달의 채용공고 생성
  public void execute() {
    System.out.println("[이달의 채용공고 / 등록]\n");

    JobsCalender jobsCalender = new JobsCalender();

    jobsCalender.setNo(Prompt.inputInt("번호? "));
    jobsCalender.setTitle(Prompt.inputString("제목? "));
    jobsCalender.setContent(Prompt.inputString("내용? "));
    jobsCalender.setStartDate(Prompt.inputDate("시작일? "));
    jobsCalender.setEndDate(Prompt.inputDate("종료일? "));

    jobsCalenderList.add(jobsCalender);

    System.out.println();
    System.out.println("이달의 채용공고 등록이 완료되었습니다.\n");
  }
}
