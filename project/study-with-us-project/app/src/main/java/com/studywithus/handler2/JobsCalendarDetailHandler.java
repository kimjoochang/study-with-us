package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.JobsCalendar;
import com.studywithus.util.Prompt;

public class JobsCalendarDetailHandler extends AbstractJobsCalendarHandler {

  public JobsCalendarDetailHandler(List<JobsCalendar> jobsCalendarList) {
    super(jobsCalendarList);
  }

  // 이달의 채용공고 상세목록
  @Override
  public void execute() {
    System.out.println("[이달의 채용공고 / 상세보기]\n");

    int no = Prompt.inputInt("번호? ");
    JobsCalendar jobsCalendar = findByNo(no);

    if (jobsCalendar == null) {
      System.out.println();
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", jobsCalendar.getTitle());
    System.out.printf("내용: %s\n", jobsCalendar.getContent());
    System.out.printf("시작일: %s\n", jobsCalendar.getStartDate());
    System.out.printf("종료일: %s\n", jobsCalendar.getEndDate());

    System.out.println();
  }
}
