package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ExamCalendar;
import com.studywithus.util.Prompt;

public class ExamCalendarDetailHandler extends AbstractExamCalendarHandler {

  public ExamCalendarDetailHandler(List<ExamCalendar> examCalendarList) {
    super(examCalendarList);
  }

  // 이달의 시험일정 상세목록
  @Override
  public void execute() {
    System.out.println("[이달의 시험일정 / 상세보기]\n");

    int no = Prompt.inputInt("번호? ");
    ExamCalendar examCalendar = findByNo(no);

    if (examCalendar == null) {
      System.out.println();
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", examCalendar.getTitle());
    System.out.printf("내용: %s\n", examCalendar.getContent());
    System.out.printf("시험일: %s\n", examCalendar.getExamDate());

    System.out.println();
  }
}
