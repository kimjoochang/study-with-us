package com.studywithus.handler.schedule;

import java.util.List;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ExamScheduleUpdateHandler extends AbstractScheduleHandler {

  public ExamScheduleUpdateHandler(List<Schedule> examScheduleList) {
    super(examScheduleList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 시험일정 / 변경]\n");

    int no = (int) request.getAttribute("scheduleNo");
    Schedule examSchedule = findByNo(no);

    System.out.println();

    if (examSchedule == null) {
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", examSchedule.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", examSchedule.getContent()));
    System.out.println();

    while (true) {
      String input = Prompt.inputString("정말 변경하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("시험일정 변경을 취소하였습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {
        examSchedule.setTitle(title);
        examSchedule.setContent(content);

        System.out.println("시험 일정을 변경하였습니다.");
        return;

      } else {
        System.out.println("다시 입력하시오.\n");
        continue;
      }
    }
  }
}
