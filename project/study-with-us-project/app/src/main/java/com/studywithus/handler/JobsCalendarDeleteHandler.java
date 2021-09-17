package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.util.Prompt;

public class JobsCalendarDeleteHandler extends AbstractCalendarHandler {

  public JobsCalendarDeleteHandler(List<Calendar> jobsCalendarList) {
    super(jobsCalendarList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[이달의 채용공고 / 삭제]\n");

    int no = Prompt.inputInt("번호: ");
    Calendar jobsCalendar = findByNo(no);

    System.out.println();

    if (jobsCalendar == null) {
      System.out.println();
      System.out.println("해당 번호의 채용공고가 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println();
      System.out.println("채용공고 삭제를 취소하였습니다.\n");
      return;
    }

    calendarList.remove(jobsCalendar);

    System.out.println();
    System.out.println("이달의 채용공고를 삭제하였습니다.\n");
  }
}
