package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ExamCalender;
import com.studywithus.util.Prompt;

public class ExamCalenderDetailHandler extends AbstractExamCalenderHandler {

  public ExamCalenderDetailHandler(List<ExamCalender> examCalenderList) {
    super(examCalenderList);
  }

  // 이달의 시험일정 상세목록
  public void execute() {
    System.out.println("[이달의 시험일정 / 상세보기]\n");

    int no = Prompt.inputInt("번호? ");
    ExamCalender examCalender = findByNo(no);

    if (examCalender == null) {
      System.out.println();
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", examCalender.getTitle());
    System.out.printf("내용: %s\n", examCalender.getContent());
    System.out.printf("시험일: %s\n", examCalender.getExamDate());

    System.out.println();
  }
}
