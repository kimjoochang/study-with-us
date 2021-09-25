package com.studywithus.handler.schedule;

import java.util.Calendar;
import java.util.List;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ExamScheduleAddHandler extends AbstractScheduleHandler {

  final int a = 3;

  public ExamScheduleAddHandler(List<Schedule> examScheduleList) {
    super(examScheduleList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("이달의 시험일정 / 등록]\n");

    Schedule examSchedule = new Schedule();
    Calendar calendar = Calendar.getInstance();

    examSchedule.setNo(Prompt.inputInt("번호를 입력하세요. > "));
    examSchedule.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    examSchedule.setWriter(AuthLogInHandler.getLoginUser());
    examSchedule.setContent(Prompt.inputString("내용을 입력하세요. > "));

    while(true) {
      String date = Prompt.inputString("시험일을 입력하세요. > " 
          + Calendar.YEAR + "-" + Calendar.MONTH + "-");

      if (Integer.parseInt(date ) > Calendar.DAY_OF_MONTH) {
        System.out.println("잘못된 날짜입니다.");
        continue;
      }
      examSchedule.setStartDate(Integer.toString(Calendar.YEAR)+"-" + 
          Integer.toString(Calendar.YEAR) + "-" 
          + date);
      break;
    }

    scheduleList.add(examSchedule);

    System.out.println();
    System.out.println("이달의 시험일정 등록이 완료되었습니다.");
  }
}