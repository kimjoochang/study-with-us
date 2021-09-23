package com.studywithus.handler.schedule;

import java.util.List;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class JobsScheduleDetailHandler extends AbstractScheduleHandler {

  public JobsScheduleDetailHandler(List<Schedule> jobsCalendarList) {
    super(jobsCalendarList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 채용공고 / 상세보기]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");
    Schedule jobsCalendar = findByNo(no);

    System.out.println();

    if (jobsCalendar == null) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", jobsCalendar.getTitle());
    System.out.printf("작성자: %s\n", jobsCalendar.getWriter().getName());
    System.out.printf("내용: %s\n", jobsCalendar.getContent());
    System.out.printf("시작일: %d-%d-%d\n", jobsCalendar.getStartYyyy(), jobsCalendar.getStartMm(), jobsCalendar.getStartDd());
    System.out.printf("종료일: %d-%d-%d\n", jobsCalendar.getYyyy(), jobsCalendar.getMm(), jobsCalendar.getDd());
    System.out.println();

    request.setAttribute("no", no);

    // 관리자인 경우
    if (jobsCalendar.getWriter() == AuthLogInHandler.getLoginUser()) {
      System.out.println("1. 수정");
      System.out.println("2. 삭제");
      System.out.println("0. 이전\n");
    }

    while (true) {
      int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");

      if (input == 1) {
        request.getRequestDispatcher("/jobsCalendar/update").forward(request);
        return;

      } else if (input == 2) {
        request.getRequestDispatcher("/jobsCalendar/delete").forward(request);
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
