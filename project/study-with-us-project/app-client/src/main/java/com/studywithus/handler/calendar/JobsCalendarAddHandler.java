package com.studywithus.handler.calendar;

import java.sql.Date;
import com.studywithus.dao.CalendarDao;
import com.studywithus.domain.Calendar;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class JobsCalendarAddHandler implements Command {

  CalendarDao scheduleDao;

  public JobsCalendarAddHandler(CalendarDao scheduleDao) {
    this.scheduleDao = scheduleDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[이달의 채용공고 / 생성]\n");

    Calendar jobsSchedule = new Calendar();

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

    scheduleDao.insert(jobsSchedule);

    System.out.println();
    System.out.println("이달의 채용공고 등록이 완료되었습니다.");
  }
}
