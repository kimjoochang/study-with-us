package com.studywithus.handler.schedule;

import com.studywithus.dao.ScheduleDao;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class JobsScheduleUpdateHandler implements Command {

  ScheduleDao scheduleDao;

  public JobsScheduleUpdateHandler(ScheduleDao scheduleDao) {
    this.scheduleDao = scheduleDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 채용공고 / 수정]\n");

    int no = (int) request.getAttribute("scheduleNo");

    Schedule jobsSchedule = scheduleDao.findByNo(no);

    if (jobsSchedule == null) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    if (jobsSchedule.getWriter().getEmail() != AuthLogInHandler.getLoginUser().getEmail()) {
      System.out.println("변경 권한이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", jobsSchedule.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 내용: ", jobsSchedule.getContent()));
    System.out.println();

    while (true) {
      String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("채용공고 수정을 취소하였습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {

        jobsSchedule.setTitle(title);
        jobsSchedule.setContent(content);

        scheduleDao.update(jobsSchedule);

        System.out.println("채용 공고를 수정하였습니다.");
        return;

      } else {
        System.out.println("유효한 값을 입력하세요.\n");
        continue;
      }
    }
  }
}
