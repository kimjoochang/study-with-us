package com.studywithus.handler.schedule;

import java.util.HashMap;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ExamScheduleDetailHandler implements Command {

  RequestAgent requestAgent;

  public ExamScheduleDetailHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 시험일정 / 상세보기]\n");

    int no = Prompt.inputInt("번호를 입력하세요. > ");

    HashMap<String,String> params = new HashMap<>();
    params.put("scheduleNo", String.valueOf(no));

    requestAgent.request("examSchedule.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 게시글이 없습니다.");
      return;
    }

    Schedule examSchedule = requestAgent.getObject(Schedule.class);

    System.out.printf("제목: %s\n", examSchedule.getTitle());
    System.out.printf("작성자: %s\n", examSchedule.getWriter().getName());
    System.out.printf("내용: %s\n", examSchedule.getContent());
    System.out.printf("시험일: %s\n", examSchedule.getStartDate());
    System.out.println();

    request.setAttribute("scheduleNo", no);

    // 관리자인 경우
    // if (examSchedule.getWriter() == AuthLogInHandler.getLoginUser()) {
    if (examSchedule.getWriter().getEmail().equals(AuthLogInHandler.getLoginUser().getEmail())) {
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
