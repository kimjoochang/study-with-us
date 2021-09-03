package com.studywithus.handler;

import com.studywithus.domain.Calender;
import com.studywithus.util.Prompt;

public abstract class AbstractExamCalenderHandler {

  protected List<ExamCalender> examCalenderList;
  
  public ExamCalenderAddHandler(List<ExamCalender> examCalenderList) {
    super(examCalenderList);
  }

  // 이달의 시험일정 번호 조회
  private Calender findByNo(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.calenders[i].getNo() == no) {
        return this.calenders[i];
      }
    }
    return null;
  }
}
