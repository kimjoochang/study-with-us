package com.studywithus.handler.schedule;

import java.util.HashMap;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ExamScheduleDeleteHandler implements Command {

  RequestAgent requestAgent;

  public ExamScheduleDeleteHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 시험일정 / 삭제]\n");

    int no = (int) request.getAttribute("scheduleNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("examSchedule.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("해당 번호의 시험일정이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까?(y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("시험일정 삭제를 취소하였습니다.");
      return;
    }

    requestAgent.request("examSchedule.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println("시험일정 삭제를 실패하였습니다.");
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    System.out.println();
    System.out.println("시험일정을 삭제하였습니다.");
  }
}
