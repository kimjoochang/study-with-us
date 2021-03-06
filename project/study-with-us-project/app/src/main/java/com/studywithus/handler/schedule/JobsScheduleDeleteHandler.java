package com.studywithus.handler.schedule;

import java.util.List;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class JobsScheduleDeleteHandler extends AbstractScheduleHandler {

  public JobsScheduleDeleteHandler(List<Schedule> jobsCalendarList) {
    super(jobsCalendarList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[이달의 채용공고 / 삭제]\n");

    int no = (int) request.getAttribute("scheduleNo");
    Schedule jobsCalendar = findByNo(no);

    System.out.println();

    if (jobsCalendar == null) {
      System.out.println();
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    while (true) {
      String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("채용공고 삭제를 취소하였습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        scheduleList.remove(jobsCalendar);
        System.out.println("이달의 채용공고를 삭제하였습니다.");
        return;
      }

      else {
        System.out.println("다시 입력하시오.\n");
        continue;
      }
    }
  }
}
