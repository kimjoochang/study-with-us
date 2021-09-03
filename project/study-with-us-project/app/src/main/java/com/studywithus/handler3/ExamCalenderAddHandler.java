package com.studywithus.handler3;

import com.studywithus.domain.Calender;
import com.studywithus.util.Prompt;

public class ExamCalenderAddHandler extends AbstractExamCalenderHandler {

  public ExamCalenderAddHandler(List<ExamCalender> examCalenderList) {
    super(examCalenderList);
  }

  // 이달의 시험일정 생성
  public void execute() {
    System.out.println("이달의 시험일정 등록]");

    Calender calender = new Calender();

    calender.setNo(Prompt.inputInt("번호 "));
    calender.setTitle(Prompt.inputString("제목 "));
    calender.setContent(Prompt.inputString("내용 "));
    calender.setStartDate(Prompt.inputDate("시험일 "));
    calender.setEndDate(Prompt.inputDate("메모 "));

    ExamCalenderHandler[] calenders = new ExamCalenderHandler[MAX_LENGTH];
  }
}
