package com.studywithus.handler3;

import com.studywithus.domain.Calender;
import com.studywithus.util.Prompt;

public class ExamCalenderDetailHandler extends AbstractExamCalenderHandler {

  public ExamCalenderAddHandler(List<ExamCalender> examCalenderList) {
    super(examCalenderList);
  }

  // 이달의 시험일정 상세목록
  public void detail() {
    System.out.println("[이달의 시험일정 상세보기]");
    int no = Prompt.inputInt("번호? ");

    Calender calender = findByNo(no);

    if (calender == null) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", calender.getTitle());
    System.out.printf("내용: %s\n", calender.getContent());
    System.out.printf("시험일: %s\n", calender.getStartDate());
    System.out.printf("메모: %s\n", calender.getMemo());
  }
}
