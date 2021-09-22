package com.studywithus.handler.calendar;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class JobsCalendarUpdateHandler extends AbstractCalendarHandler {

  public JobsCalendarUpdateHandler(List<Calendar> jobsCalendarList) {
    super(jobsCalendarList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 채용공고 / 수정]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");
    Calendar jobsCalendar = findByNo(no);

    System.out.println();

    if (jobsCalendar == null) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", jobsCalendar.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", jobsCalendar.getContent()));
    System.out.println();

    while (true) {
      String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("채용공고 수정을 취소하였습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        jobsCalendar.setTitle(title);
        jobsCalendar.setContent(content);

        System.out.println("채용 공고를 수정하였습니다.");
        return;

      } else {
        System.out.println("다시 입력하시오.\n");
        continue;
      }
    }
  }
}
