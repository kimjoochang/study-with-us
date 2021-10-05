package com.studywithus.handler.schedule;

import java.sql.Date;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class JobsScheduleAddHandler implements Command {

  RequestAgent requestAgent;

  public JobsScheduleAddHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 채용공고 / 등록]\n");

    Schedule jobsSchedule = new Schedule();

    jobsSchedule.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    jobsSchedule.setWriter(AuthLogInHandler.getLoginUser());
    jobsSchedule.setContent(Prompt.inputString("내용을 입력하세요. > "));

    while (true) {
      jobsSchedule.setStartDate(Prompt.inputDate("시작일을 입력하세요. ex) YYYY-MM-DD > "));

      // 현재 날짜 > 시작일인 경우
      if (new Date(System.currentTimeMillis()).compareTo(jobsSchedule.getStartDate()) == 1) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    while (true) {
      jobsSchedule.setEndDate(Prompt.inputDate("종료일을 입력하세요. ex) YYYY-MM-DD > "));

      // 시작일 < 종료일이 아닌 경우
      if (jobsSchedule.getEndDate().compareTo(jobsSchedule.getStartDate()) != 1) {
        System.out.println("종료일은 시작일 이후로 설정하세요.\n");
        continue;

        // 현재 날짜 > 시험일인 경우
      } else if (new Date(System.currentTimeMillis()).compareTo(jobsSchedule.getStartDate()) == 1) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    requestAgent.request("jobsSchedule.insert", jobsSchedule);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("게시글 저장에 실패하였습니다.");
      return;
    }


    System.out.println();
    System.out.println("이달의 채용공고 등록이 완료되었습니다.");
  }
}
