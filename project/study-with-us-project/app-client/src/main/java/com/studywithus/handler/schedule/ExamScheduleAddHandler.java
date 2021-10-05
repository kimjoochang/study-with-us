package com.studywithus.handler.schedule;

import java.sql.Date;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ExamScheduleAddHandler implements Command {

  RequestAgent requestAgent;

  public ExamScheduleAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("이달의 시험일정 / 생성]\n");

    Schedule examSchedule = new Schedule();

    examSchedule.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    examSchedule.setWriter(AuthLogInHandler.getLoginUser());
    examSchedule.setContent(Prompt.inputString("내용을 입력하세요. > "));

    while (true) {
      examSchedule.setStartDate(Prompt.inputDate("시험일을 입력하세요. ex) YYYY-MM-DD > "));

      // 현재 날짜 > 시험일인 경우
      if (new Date(System.currentTimeMillis()).compareTo(examSchedule.getStartDate()) == 1) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    requestAgent.request("examSchedule.insert", examSchedule);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 저장에 실패하였습니다.");
      return;
    }

    System.out.println();
    System.out.println("이달의 시험일정 등록이 완료되었습니다.");
  }
}
