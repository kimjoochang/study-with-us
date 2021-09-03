package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.JobsCalender;
import com.studywithus.util.Prompt;

public class JobsCalenderDeleteHandler extends AbstractJobsCalenderHandler {

  public JobsCalenderDeleteHandler(List<JobsCalender> jobsCalenderList) {
    super(jobsCalenderList);
  }

  // 이달의 채용공고 삭제
  public void execute() {
    System.out.println("[이달의 채용공고 / 삭제]\n");

    int no = Prompt.inputInt("번호? ");

    System.out.println();

    JobsCalender jobsCalender = findByNo(no);

    if (jobsCalender == null) {
      System.out.println();
      System.out.println("해당 번호의 채용공고가 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("채용공고 삭제를 취소하였습니다.\n");
      return;
    }

    jobsCalenderList.remove(jobsCalender);

    System.out.println();
    System.out.println("이달의 채용공고를 삭제하였습니다.\n");
  }
}