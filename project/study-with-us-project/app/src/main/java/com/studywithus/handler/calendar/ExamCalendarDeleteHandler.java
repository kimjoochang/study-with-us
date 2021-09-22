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

    int no = Prompt.inputInt("번호를 입력하세요. > ");
    Calendar examCalendar = findByNo(no);

    System.out.println();

    if (examCalendar == null) {
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    while (true) {
      String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("시험일정 삭제를 취소하였습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        calendarList.remove(examCalendar);
        System.out.println("이달의 시험일정을 삭제하였습니다.");
        return;
      }

      else {
        System.out.println("다시 입력하시오.\n");
        continue;
      }
    }
  }
}
