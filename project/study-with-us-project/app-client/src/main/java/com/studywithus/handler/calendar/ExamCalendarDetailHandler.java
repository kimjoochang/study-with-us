package com.studywithus.handler.calendar;

import com.studywithus.dao.CalendarDao;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ExamCalendarDetailHandler implements Command {

  CalendarDao scheduleDao;

  public ExamCalendarDetailHandler(CalendarDao scheduleDao) {
    this.scheduleDao = scheduleDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 시험일정 / 상세보기]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");

    Calendar examSchedule = scheduleDao.findByNo(no);

    if (examSchedule == null) {
      System.out.println("해당 번호의 일정이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", examSchedule.getTitle());
    System.out.printf("작성자: %s\n", examSchedule.getWriter().getName());
    System.out.printf("내용: %s\n", examSchedule.getContent());
    System.out.printf("시험일: %s\n", examSchedule.getStartDate());
    System.out.println();

    request.setAttribute("scheduleNo", no);

    System.out.println("1. 수정");
    System.out.println("2. 삭제");
    System.out.println("0. 이전\n");

    while (true) {
      int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");

      if (input == 1) {
        request.getRequestDispatcher("/examSchedule/update").forward(request);
        return;

      } else if (input == 2) {
        request.getRequestDispatcher("/examSchedule/delete").forward(request);
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
