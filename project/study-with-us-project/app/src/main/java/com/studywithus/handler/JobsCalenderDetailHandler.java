package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.JobsCalender;
import com.studywithus.util.Prompt;

public class JobsCalenderDetailHandler extends AbstractJobsCalenderHandler {

  public JobsCalenderDetailHandler(List<JobsCalender> jobsCalenderList) {
    super(jobsCalenderList);
  }

  // 이달의 채용공고 상세목록
  public void execute() {
    System.out.println("[이달의 채용공고 / 상세보기]\n");

    int no = Prompt.inputInt("번호? ");
    JobsCalender jobsCalender = findByNo(no);

    if (jobsCalender == null) {
      System.out.println();
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", jobsCalender.getTitle());
    System.out.printf("내용: %s\n", jobsCalender.getContent());
    System.out.printf("시작일: %s\n", jobsCalender.getStartDate());
    System.out.printf("종료일: %s\n", jobsCalender.getEndDate());

    System.out.println();
  }
}
