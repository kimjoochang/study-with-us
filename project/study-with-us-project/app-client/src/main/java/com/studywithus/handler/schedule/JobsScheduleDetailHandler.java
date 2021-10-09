package com.studywithus.handler.schedule;

import com.studywithus.dao.ScheduleDao;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class JobsScheduleDetailHandler implements Command {

  ScheduleDao scheduleDao;

  public JobsScheduleDetailHandler(ScheduleDao scheduleDao) {
    this.scheduleDao = scheduleDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 채용공고 / 상세보기]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Schedule jobsSchedule = scheduleDao.findByNo(no);

    if (jobsSchedule == null) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", jobsSchedule.getTitle());
    System.out.printf("작성자: %s\n", jobsSchedule.getWriter().getName());
    System.out.printf("내용: %s\n", jobsSchedule.getContent());
    System.out.printf("시작일: %s\n", jobsSchedule.getStartDate());
    System.out.printf("종료일: %s\n", jobsSchedule.getEndDate());
    System.out.println();

    request.setAttribute("scheduleNo", no);

    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("0. 이전\n");

    while (true) {
      int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");

      if (input == 1) {
        request.getRequestDispatcher("/jobsSchedule/update").forward(request);
        return;

      } else if (input == 2) {
        request.getRequestDispatcher("/jobsSchedule/delete").forward(request);
        return;

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 메뉴 번호입니다.\n");
        continue;
      }
    }
  }
}
