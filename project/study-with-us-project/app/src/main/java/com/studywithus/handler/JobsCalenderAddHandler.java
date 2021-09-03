package com.studywithus.handler;

import com.studywithus.domain.Calender;
import com.studywithus.util.Prompt;

public class JobsCalenderAddHandler extends AbstractJobsCalenderHandler {

  public AbstractJobsCalenderHandler(List<JobsCalender> jobsCalenderHandlerList) {
    super(jobsCalenderHandlerList);
  }

  // 이달의 채용공고 생성
  public void add() {
    System.out.println("[이달의 채용공고 등록]");

    Calender calender = new Calender();

    calender.setNo(Prompt.inputInt("번호 "));
    calender.setTitle(Prompt.inputString("제목 "));
    calender.setContent(Prompt.inputString("내용 "));
    calender.setStartDate(Prompt.inputDate("시작일 "));
    calender.setEndDate(Prompt.inputDate("종료일 "));
    calender.setEndDate(Prompt.inputDate("메모 "));

    JobsCalenderHandler[] calenders = new JobsCalenderHandler[MAX_LENGTH];
  }
}
