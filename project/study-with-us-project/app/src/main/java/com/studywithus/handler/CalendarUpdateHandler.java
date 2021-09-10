package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.util.Prompt;

public class CalendarUpdateHandler extends AbstractCalendarHandler{

  public CalendarUpdateHandler(List<Calendar> calendarList) {
    super(calendarList);
  }

  // 캘린더 수정
  @Override
  public void execute() {
    System.out.println("[캘린더 / 수정]\n");

    int no = Prompt.inputInt("번호? ");
    System.out.println();

    Calendar calendar = findByNo(no);

    if (calendar == null) {
      System.out.println();
      System.out.println("해당 번호의 캘린더가 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", calendar.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", calendar.getContent()));
    String input = Prompt.inputString("정말 수정하시겠습니까?(y/N) ");

    System.out.println();

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("캘린더 수정을 취소하였습니다.");
      return;
    }

    calendar.setTitle(title);
    calendar.setContent(content);

    System.out.println();
    System.out.println("캘린더를 수정하였습니다.");
  }
}
