package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Calendar;
import com.studywithus.util.Prompt;

public class CalendarDetailHandler extends AbstractCalendarHandler{

  public CalendarDetailHandler(List<Calendar> calendarList) {
    super(calendarList);
  }

  // 캘린더 상세보기
  @Override
  public void execute() {
    System.out.println("[캘린더 / 상세보기]\n");

    // 다른 게시판 상세보기와 성격이 다른 것 같아서 논의 후 메서드 내용 수정 들어가야 할 듯
    int no = Prompt.inputInt("번호? ");
    Calendar calendar = findByNo(no);

    if (calendar == null) {
      System.out.println();
      System.out.println("해당 번호의 캘린더가 없습니다.");
      return;
    }

    System.out.printf("제목: %s\n", calendar.getTitle());
    System.out.printf("내용: %s\n", calendar.getContent());
    System.out.printf("시작일: %s\n", calendar.getStartDate());
    System.out.printf("종료일: %s\n", calendar.getEndDate());

    System.out.println();
  }
}
