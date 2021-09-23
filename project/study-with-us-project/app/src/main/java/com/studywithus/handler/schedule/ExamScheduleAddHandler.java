package com.studywithus.handler.schedule;

import java.util.List;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ExamScheduleAddHandler extends AbstractScheduleHandler {

  public ExamScheduleAddHandler(List<Schedule> examScheduleList) {
    super(examScheduleList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("이달의 시험일정 / 등록]\n");

    Schedule examSchedule = new Schedule();

    examSchedule.setNo(Prompt.inputInt("번호를 입력하세요. > "));
    examSchedule.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    examSchedule.setWriter(AuthLogInHandler.getLoginUser());
    examSchedule.setContent(Prompt.inputString("내용을 입력하세요. > "));

    System.out.println("시험일을 입력하세요.");

    // 년
    while (true) {
      examSchedule.setYyyy(Prompt.inputInt("YYYY > "));

      if (examSchedule.getYyyy() < 2021) {
        System.out.println("유효한 연도를 입력하시오.\n");
        continue;

      } else {
        break;
      }
    }

    // 월
    while (true) {
      examSchedule.setMm(Prompt.inputInt("MM > "));

      if (!(1 <= examSchedule.getMm() && examSchedule.getMm() <= 12)) {
        System.out.println("유효한 월을 입력하시오.\n");
        continue;

      } else {
        break;
      }
    }

    // 일
    while (true) {
      examSchedule.setDd(Prompt.inputInt("DD > "));

      // 1 ~ 31일
      switch (examSchedule.getMm()) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:

          if (!(examSchedule.getDd() >= 1 && examSchedule.getDd() <= 31)) {
            System.out.println("유효한 일을 입력하시오.\n");
            continue;

          } else {
            break;
          }
      }

      // 1 ~ 30일
      switch (examSchedule.getMm()) {
        case 4:
        case 6:
        case 9:
        case 11:

          if (!(examSchedule.getDd() >= 1 && examSchedule.getDd() <= 30)) {
            System.out.println("유효한 일을 입력하시오.\n");
            continue;

          } else {
            break;
          }
      }

      // 1 ~ 28일
      switch (examSchedule.getMm()) {
        case 2:

          if (!(examSchedule.getDd() >= 1 && examSchedule.getDd() <= 28)) {
            System.out.println("유효한 일을 입력하시오.\n");
            continue;

          } else {
            break;
          }
      }
      break;
    }

    scheduleList.add(examSchedule);

    System.out.println();
    System.out.println("이달의 시험일정 등록이 완료되었습니다.");
  }
}
