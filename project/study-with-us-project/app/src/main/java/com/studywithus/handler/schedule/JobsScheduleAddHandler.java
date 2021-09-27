package com.studywithus.handler.schedule;

import java.time.LocalDate;
import java.util.Calendar;
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

    Schedule lastElement = null;
    Schedule jobsCalendar = new Schedule();
    Calendar calendar = Calendar.getInstance();
    String[] arr = new String[2];
    LocalDate now = LocalDate.now();
    // calendar 인스턴스의 월 정보를 지금 현재 월로 세팅
    calendar.set(Calendar.MONTH, now.getMonthValue() - 1);
    String startDate;
    String endDate;
    if (!scheduleList.isEmpty()) {
      lastElement = scheduleList.get(scheduleList.size() - 1);
      jobsCalendar.setNo(lastElement.getNo() + 1);
    } else {
      jobsCalendar.setNo(1);
    }
    jobsCalendar.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    jobsCalendar.setWriter(AuthLogInHandler.getLoginUser());
    jobsCalendar.setContent(Prompt.inputString("내용을 입력하세요. > "));

    // 시작일 입력
    while (true) {
      startDate = Prompt.inputString("채용공고 시작일을 입력하세요. > " + Integer.toString(now.getYear()) + "-"
          + Integer.toString(now.getMonthValue()) + "-");

      // 만약 calendar 인스턴스에 세팅된 월의 최대 일보다 입력값이 크다면 잘못된 날짜 출력
      if (Integer.parseInt(startDate) > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
        System.out.println("잘못된 날짜입니다.");
        continue;
      }
      jobsCalendar.setStartDate(Integer.toString(now.getYear()) + "-"
          + Integer.toString(now.getMonthValue()) + "-" + startDate);
      break;
    }

    // 종료일 입력
    while (true) {
      endDate = Prompt.inputString("채용공고 종료일을 입력하세요. > " + Integer.toString(now.getYear()) + "-");

      if (!endDate.contains("-")) {
        System.out.println("입력 형태가 올바르지 않습니다. ex) MM-DD");
        continue;
      }

      arr = endDate.split("-");

      // 입력값의 월이 현재 월과 같을 때,
      if (Integer.parseInt(arr[0]) == now.getMonthValue()) {

        // 만약 calendar 인스턴스에 세팅된 월의 최대 일보다 입력값의 일이 크다면 잘못된 날짜 출력
        if (Integer.parseInt(arr[1]) > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
          System.out.println("잘못된 날짜입니다.");
          continue;
        }

        // 만약 시작일보다 종료일이 빠르다면
        if (Integer.parseInt(arr[1]) < Integer.parseInt(startDate)) {
          System.out.println("종료일은 시작일 이후로 입력해주세요. ");
          continue;
        }
        jobsCalendar.setEndDate(Integer.toString(now.getYear()) + "-" + endDate);
        break;

        // 입력값의 월이 현재 월보다 클 경우,
      } else if (Integer.parseInt(arr[0]) > now.getMonthValue()) {
        // calendar 인스턴스의 월 정보를 입력 값으로 세팅
        calendar.set(Calendar.MONTH, Integer.parseInt(arr[1]) - 1);
        // 만약 calendar 인스턴스에 세팅된 월의 최대 일보다 입력값이 크다면 잘못된 날짜 출력
        if (Integer.parseInt(arr[1]) > calendar.getActualMaximum(Calendar.DAY_OF_MONTH)) {
          System.out.println("잘못된 날짜입니다.");
          continue;
        }
        jobsCalendar.setEndDate(Integer.toString(now.getYear()) + "-" + endDate);
        break;

        // 입력값의 월이 현재 월보다 작을 경우,
      } else {
        System.out.println("종료일은 시작일 이후로 입력해주세요. ");
      }
    }


    scheduleList.add(jobsCalendar);

    System.out.println();
    System.out.println("이달의 채용공고 등록이 완료되었습니다.");
  }
}
