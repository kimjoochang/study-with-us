package com.studywithus.handler;

import com.studywithus.domain.Calender;
import com.studywithus.util.Prompt;

public class JobsCalenderUpdateHandler extends AbstractJobsCalenderHandler {

  public AbstractJobsCalenderHandler(List<JobsCalender> jobsCalenderHandlerList) {
    super(jobsCalenderHandlerList);
  }

  // 이달의 채용공고 변경
  public void update() {
    System.out.println("[이달의 채용공고 변경]");
    int no = Prompt.inputInt("번호? ");

    Calender calender = findByNo(no);

    if (calender == null) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s)? ", calender.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)? ", calender.getContent()));
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("채용공고 변경을 취소하였습니다.");
      return;
    }

    calender.setTitle(title);
    calender.setContent(content);
    System.out.println("채용 공고를 변경하였습니다.");
  }
}
