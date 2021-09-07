package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ExamCalendar;
import com.studywithus.util.Prompt;

public class ExamCalendarUpdateHandler extends AbstractExamCalendarHandler {

  public ExamCalendarUpdateHandler(List<ExamCalendar> examCalendarList) {
    super(examCalendarList);
  }

  // 이달의 시험일정 변경
  @Override
  public void execute() {
    System.out.println("[이달의 시험일정 / 변경]\n");

    int no = Prompt.inputInt("번호? ");
    System.out.println();

    ExamCalendar examCalendar = findByNo(no);

    if (examCalendar == null) {
      System.out.println();
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", examCalendar.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", examCalendar.getContent()));
    String input = Prompt.inputString("정말 변경하시겠습니까?(y/N) ");

    System.out.println();

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("시험일정 변경을 취소하였습니다.");
      return;
    }

    examCalendar.setTitle(title);
    examCalendar.setContent(content);

    System.out.println();
    System.out.println("시험 일정을 변경하였습니다.");
  }
}
