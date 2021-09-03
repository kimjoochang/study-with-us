package com.studywithus.handler;

import com.studywithus.domain.Calender;
import com.studywithus.util.Prompt;

public class JobsCalenderDeleteHandler extends AbstractJobsCalenderHandler {

  public AbstractJobsCalenderHandler(List<JobsCalender> jobsCalenderHandlerList) {
    super(jobsCalenderHandlerList);
  }
  
  // 이달의 채용공고 삭제
  public void delete() {
    System.out.println("[이달의 채용공고 삭제]");

    int no = Prompt.inputInt("번호? ");
    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("채용공고 삭제를 취소하였습니다.");
      return;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.calenders[i - 1] = this.calenders[i];
    }

    this.calenders[--this.size] = null;
    System.out.println("이달의 채용공고를 삭제하였습니다.");
  }
}
