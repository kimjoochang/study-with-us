package com.studywithus.handler.schedule;

import java.sql.Date;
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

    examSchedule.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    examSchedule.setWriter(AuthLogInHandler.getLoginUser());
    examSchedule.setContent(Prompt.inputString("내용을 입력하세요. > "));
    examSchedule.setStartDate(Prompt.inputDate("시험일을 입력하세요. ex) YYYY-MM-DD > "));

    while (true) {
      examSchedule.setEndDate(Prompt.inputDate("종료일을 입력하세요. ex) YYYY-MM-DD > "));

      // 현재 날짜 > 시험일인 경우
      if (new Date(System.currentTimeMillis()).compareTo(examSchedule.getStartDate()) == 1) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    scheduleList.add(examSchedule);

    System.out.println();
    System.out.println("이달의 시험일정 등록이 완료되었습니다.");
  }
}

// ExamScheduleAddHandler Ver.1
// final int a = 3;
//
// Schedule lastElement = null;
// Schedule examSchedule = new Schedule();
// LocalDate now = LocalDate.now();
// Calendar calendar = Calendar.getInstance();
// // calendar 인스턴스의 월 정보를 지금 현재 월로 세팅
// calendar.set(Calendar.MONTH, now.getMonthValue() - 1);
//
// if (!scheduleList.isEmpty()) {
// lastElement = scheduleList.get(scheduleList.size() - 1);
// examSchedule.setNo(lastElement.getNo() + 1);
// } else {
// examSchedule.setNo(1);
// }
//
// while (true) {
// String date = Prompt.inputString("시험일을 입력하세요. > " + Integer.toString(now.getYear()) + "-"
// + Integer.toString(now.getMonthValue()) + "-");
//
// // 만약 calendar 인스턴스에 세팅된 월의 최대 일보다 입력값이 크다면 잘못된 날짜 출력
// if (Integer.parseInt(date) > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
// System.out.println("잘못된 날짜입니다.");
// continue;
// }
// examSchedule.setStartDate(Integer.toString(now.getYear()) + "-"
// + Integer.toString(now.getMonthValue()) + "-" + date);
// break;
// }
