package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ExamCalendar;

public abstract class AbstractExamCalendarHandler implements Command {

  protected List<ExamCalendar> examCalendarList;

  public AbstractExamCalendarHandler(List<ExamCalendar> examCalendarList) {
    this.examCalendarList = examCalendarList;
  }

  // 이달의 시험일정 번호 조회
  protected ExamCalendar findByNo(int no) {
    for (ExamCalendar examCalendar : examCalendarList) {
      if (examCalendar.getNo() == no) {
        return examCalendar;
      }
    }
    return null;
  }
}
