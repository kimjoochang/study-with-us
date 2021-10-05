package com.studywithus.handler.schedule;

import java.util.HashMap;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class JobsScheduleUpdateHandler implements Command {

  RequestAgent requestAgent;

  public JobsScheduleUpdateHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 채용공고 / 수정]\n");

    int no = (int) request.getAttribute("scheduleNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no)); //?

    requestAgent.request("jobsSchedule.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 채용공고가 없습니다.");
      return;
    }

    Schedule jobsSchedule = requestAgent.getObject(Schedule.class);

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

        requestAgent.request("jobsSchedule.update", jobsSchedule);

        System.out.println("채용 공고를 수정하였습니다.");
        return;

      } else if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
        System.out.println("시험일정 변경을 실패하였습니다.");
        System.out.println(requestAgent.getObject(String.class));
        return;

      } else {
        System.out.println("유효한 값을 입력하세요.\n");
        continue;
      }
    }
  }
}
