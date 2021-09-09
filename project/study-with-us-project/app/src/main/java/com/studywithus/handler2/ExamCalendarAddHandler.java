package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ExamCalendar;
import com.studywithus.util.Prompt;

public class ExamCalendarAddHandler extends AbstractExamCalendarHandler {

  public ExamCalendarAddHandler(List<ExamCalendar> examCalendarList) {
    super(examCalendarList);
  }

  // 이달의 시험일정 생성
  @Override
  public void execute() {
    System.out.println("이달의 시험일정 / 등록]\n");

    ExamCalendar examCalendar = new ExamCalendar();

    examCalendar.setNo(Prompt.inputInt("번호? "));
    examCalendar.setTitle(Prompt.inputString("제목? "));
    examCalendar.setContent(Prompt.inputString("내용? "));
    examCalendar.setExamDate(Prompt.inputString("시험일? "));

    examCalendarList.add(examCalendar);

    System.out.println();
    System.out.println("이달의 시험일정 등록이 완료되었습니다.\n");
  }
}
