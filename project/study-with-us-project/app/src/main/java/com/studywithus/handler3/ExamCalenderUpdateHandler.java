package com.studywithus.handler3;

import com.studywithus.domain.Calender;
import com.studywithus.util.Prompt;

public class ExamCalenderUpdateHandler extends AbstractExamCalenderHandler {

  public ExamCalenderAddHandler(List<ExamCalender> examCalenderList) {
    super(examCalenderList);
  }

  // 이달의 시험일정 변경
  public void update() {
    System.out.println("[이달의 시험일정 변경]");

    int no = Prompt.inputInt("번호? ");

    Calender calender = findByNo(no);

    if (calender == null) {
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("제목(%s)? ", calender.getTitle()));
    String content = Prompt.inputString(String.format("내용(%s)? ", calender.getContent()));
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("시험일정 변경을 취소하였습니다.");
      return;
    }

    calender.setTitle(title);
    calender.setContent(content);
    System.out.println("시험일정을 변경하였습니다.");
  }
}
