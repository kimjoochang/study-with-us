package com.studywithus.handler.schedule;

import com.studywithus.dao.ScheduleDao;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ExamScheduleUpdateHandler implements Command {

  ScheduleDao scheduleDao;

  public ExamScheduleUpdateHandler(ScheduleDao scheduleDao) {
    this.scheduleDao = scheduleDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 시험일정 / 수정]\n");

    int no = (int) request.getAttribute("scheduleNo");

    Schedule examSchedule = scheduleDao.findByNo(no);

    if (examSchedule == null) {
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", examSchedule.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", examSchedule.getContent()));
    System.out.println();

    while (true) {
      String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("시험일정 수정을 취소하였습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {

        examSchedule.setTitle(title);
        examSchedule.setContent(content);

        scheduleDao.update(examSchedule);

      } else {
        System.out.println("유효한 값을 입력하세요.\n");
        continue;
      }
    }
  }
}
