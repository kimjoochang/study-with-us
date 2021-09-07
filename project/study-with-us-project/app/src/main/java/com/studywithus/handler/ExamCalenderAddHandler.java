package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ExamCalender;
import com.studywithus.util.Prompt;

public class ExamCalenderAddHandler extends AbstractExamCalenderHandler {

  public ExamCalenderAddHandler(List<ExamCalender> examCalenderList) {
    super(examCalenderList);
  }

  // 이달의 시험일정 생성
  public void execute() {
    System.out.println("이달의 시험일정 / 등록]\n");

    ExamCalender examCalender = new ExamCalender();

    examCalender.setNo(Prompt.inputInt("번호? "));
    examCalender.setTitle(Prompt.inputString("제목? "));
    examCalender.setContent(Prompt.inputString("내용? "));
    examCalender.setExamDate(Prompt.inputString("시험일? "));

    examCalenderList.add(examCalender);

    System.out.println();
    System.out.println("이달의 시험일정 등록이 완료되었습니다.\n");
  }
}
