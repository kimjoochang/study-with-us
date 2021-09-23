package com.studywithus.handler.schedule;

import java.util.List;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ExamScheduleDetailHandler extends AbstractScheduleHandler {

  List<Schedule> examSchedule;

  public ExamScheduleDetailHandler(List<Schedule> examScheduleList) {
    super(examScheduleList);
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 시험일정 / 상세보기]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");
    Schedule examSchedule = findByNo(no);

    System.out.println();

    if (examSchedule == null) {
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", examSchedule.getTitle());
    System.out.printf("작성자: %s\n", examSchedule.getWriter().getName());
    System.out.printf("내용: %s\n", examSchedule.getContent());
    System.out.printf("시험일: %d-%d-%d\n", examSchedule.getYyyy(), examSchedule.getMm(), examSchedule.getDd());
    System.out.println();

    request.setAttribute("no", no);

    // 관리자인 경우
    if (examSchedule.getWriter() == AuthLogInHandler.getLoginUser()) {
      System.out.println("1. 변경");
      System.out.println("2. 삭제");
      System.out.println("0. 이전\n");
    }

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
