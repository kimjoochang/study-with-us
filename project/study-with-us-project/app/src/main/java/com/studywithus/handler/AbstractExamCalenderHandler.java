package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.ExamCalender;

public abstract class AbstractExamCalenderHandler implements Command {

  protected List<ExamCalender> examCalenderList;

  public AbstractExamCalenderHandler(List<ExamCalender> examCalenderList) {
    this.examCalenderList = examCalenderList;
  }

  // 이달의 시험일정 번호 조회
  protected ExamCalender findByNo(int no) {
    for (ExamCalender examCalender : examCalenderList) {
      if (examCalender.getNo() == no) {
        return examCalender;
      }
    }
    return null;
  }
}
