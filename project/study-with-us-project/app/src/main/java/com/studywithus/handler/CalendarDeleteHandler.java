package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.util.Prompt;

public class CalendarDeleteHandler extends AbstractCalendarHandler{

  public CalendarDeleteHandler(List<Calendar> calendarList) {
    super(calendarList);
  }

  // 캘린더 삭제
  @Override
  public void execute() {
    System.out.println("[캘린더 / 삭제]\n");

    int no = Prompt.inputInt("번호? ");

    System.out.println();

    Calendar calendar = findByNo(no);

    if (calendar == null) {
      System.out.println();
      System.out.println("해당 번호의 캘린더가 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("캘린더 삭제를 취소하였습니다.\n");
      return;
    }

    calendarList.remove(calendar);

    System.out.println();
    System.out.println("이달의 채용공고를 삭제하였습니다.\n");
  }
}
