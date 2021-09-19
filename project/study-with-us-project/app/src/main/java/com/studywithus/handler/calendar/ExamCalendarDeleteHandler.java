package com.studywithus.handler.calendar;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ExamCalendarDeleteHandler extends AbstractCalendarHandler {

  public ExamCalendarDeleteHandler(List<Calendar> examCalendarList) {
    super(examCalendarList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[이달의 시험일정 / 삭제]\n");

    int no = Prompt.inputInt("번호? ");

    System.out.println();

    Calendar examCalendar = findByNo(no);

    if (examCalendar == null) {
      System.out.println();
      System.out.println("해당 번호의 시험일정이 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("시험일정 삭제를 취소하였습니다.\n");
      return;
    }

    calendarList.remove(examCalendar);

    System.out.println();
    System.out.println("이달의 시험일정을 삭제하였습니다.\n");
  }
}
