package com.studywithus.handler.calendar;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ExamCalendarUpdateHandler extends AbstractCalendarHandler {

  public ExamCalendarUpdateHandler(List<Calendar> examCalendarList) {
    super(examCalendarList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 시험일정 / 변경]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");
    Calendar examCalendar = findByNo(no);

    System.out.println();

    if (examCalendar == null) {
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", examCalendar.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", examCalendar.getContent()));
    System.out.println();

    while (true) {
      String input = Prompt.inputString("정말 변경하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("시험일정 변경을 취소하였습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        examCalendar.setTitle(title);
        examCalendar.setContent(content);

        System.out.println("시험 일정을 변경하였습니다.");
        return;

      } else {
        System.out.println("다시 입력하시오.\n");
        continue;
      }
    }
  }
}
