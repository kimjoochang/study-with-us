package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ExamCalender;
import com.studywithus.util.Prompt;

public class ExamCalenderDeleteHandler extends AbstractExamCalenderHandler {

  public ExamCalenderDeleteHandler(List<ExamCalender> examCalenderList) {
    super(examCalenderList);
  }

  // 이달의 시험일정 삭제
  public void execute() {
    System.out.println("[이달의 시험일정 / 삭제]\n");

    int no = Prompt.inputInt("번호? ");

    System.out.println();

    ExamCalender examCalender = findByNo(no);

    if (examCalender == null) {
      System.out.println();
      System.out.println("해당 번호의 시험일정이 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("시험일정 삭제를 취소하였습니다.\n");
      return;
    }

    examCalenderList.remove(examCalender);

    System.out.println();
    System.out.println("이달의 시험일정을 삭제하였습니다.\n");
  }
}
