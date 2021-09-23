package com.studywithus.handler.schedule;

import java.util.List;
import com.studywithus.domain.Schedule;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class JobsScheduleAddHandler extends AbstractScheduleHandler {

  public JobsScheduleAddHandler(List<Schedule> jobsCalendarList) {
    super(jobsCalendarList);
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[이달의 채용공고 / 등록]\n");

    Schedule jobsCalendar= new Schedule();

    jobsCalendar.setNo(Prompt.inputInt("번호를 입력하세요. > "));
    jobsCalendar.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    jobsCalendar.setWriter(AuthLogInHandler.getLoginUser());
    jobsCalendar.setContent(Prompt.inputString("내용을 입력하세요. > "));

    System.out.println("시작일을 입력하세요.");

    // 년
    while (true) {
      jobsCalendar.setStartYyyy(Prompt.inputInt("YYYY > "));

      if (jobsCalendar.getStartYyyy() < 2021) {
        System.out.println("유효한 연도를 입력하시오.\n");
        continue;

      } else {
        break;
      }
    }

    // 월
    while (true) {
      jobsCalendar.setStartMm(Prompt.inputInt("MM > "));

      if (!(1 <= jobsCalendar.getStartMm() && jobsCalendar.getStartMm() <= 12)) {
        System.out.println("유효한 월을 입력하시오.\n");
        continue;

      } else {
        break;
      }
    }

    // 일
    while (true) {
      jobsCalendar.setStartDd(Prompt.inputInt("DD > "));

      // 1 ~ 31일
      switch (jobsCalendar.getStartMm()) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:

          if (!(jobsCalendar.getStartDd() >= 1 && jobsCalendar.getStartDd() <= 31)) {
            System.out.println("유효한 일을 입력하시오.\n");
            continue;

          } else {
            break;
          }
      }

      // 1 ~ 30일
      switch (jobsCalendar.getStartMm()) {
        case 4:
        case 6:
        case 9:
        case 11:

          if (!(jobsCalendar.getStartDd() >= 1 && jobsCalendar.getStartDd() <= 30)) {
            System.out.println("유효한 일을 입력하시오.\n");
            continue;

          } else {
            break;
          }
      }

      // 1 ~ 28일
      switch (jobsCalendar.getStartMm()) {
        case 2:

          if (!(jobsCalendar.getStartDd() >= 1 && jobsCalendar.getStartDd() <= 28)) {
            System.out.println("유효한 일을 입력하시오.\n");
            continue;

          } else {
            break;
          }
      }
      break;
    }

    System.out.println("종료일을 입력하세요.");

    // 년
    while (true) {
      jobsCalendar.setYyyy(Prompt.inputInt("YYYY > "));

      if (jobsCalendar.getStartYyyy() > jobsCalendar.getYyyy()) {
        System.out.println("종료일은 시작일 이후로 설정하시오.\n");
        continue;

      } else {
        break;
      }
    }

    // 월
    while (true) {
      jobsCalendar.setMm(Prompt.inputInt("MM > "));

      if (!(1 <= jobsCalendar.getMm() && jobsCalendar.getMm() <= 12)) {
        System.out.println("유효한 월을 입력하시오.\n");
        continue;

      } else {
        break;
      }
    }

    // 일
    while (true) {
      jobsCalendar.setDd(Prompt.inputInt("DD > "));

      // 1 ~ 31일
      switch (jobsCalendar.getMm()) {
        case 1:
        case 3:
        case 5:
        case 7:
        case 8:
        case 10:
        case 12:

          if (!(jobsCalendar.getDd() >= 1 && jobsCalendar.getDd() <= 31)) {
            System.out.println("유효한 일을 입력하시오.\n");
            continue;

          } else {
            break;
          }
      }

      // 1 ~ 30일
      switch (jobsCalendar.getMm()) {
        case 4:
        case 6:
        case 9:
        case 11:

          if (!(jobsCalendar.getDd() >= 1 && jobsCalendar.getDd() <= 30)) {
            System.out.println("유효한 일을 입력하시오.\n");
            continue;

          } else {
            break;
          }
      }

      // 1 ~ 28일
      switch (jobsCalendar.getMm()) {
        case 2:

          if (!(jobsCalendar.getDd() >= 1 && jobsCalendar.getDd() <= 28)) {
            System.out.println("유효한 일을 입력하시오.\n");
            continue;

          } else {
            break;
          }
      }
      break;
    }

    scheduleList.add(jobsCalendar);

    System.out.println();
    System.out.println("이달의 채용공고 등록이 완료되었습니다.");
  }
}
