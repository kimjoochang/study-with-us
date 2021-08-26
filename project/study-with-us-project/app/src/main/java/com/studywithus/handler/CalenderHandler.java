package com.studywithus.handler;

import com.studywithus.util.Prompt;

public class CalenderHandler {

  static final int MAX_LENGTH = 5;

  CalenderHandler[] calenders = new CalenderHandler[MAX_LENGTH];
  int size = 0;

  public void add() {
    System.out.println("[채용공고 등록]");

    CalenderHandler calender = new CalenderHandler();

    calender.title = Prompt.inputString("제목 ");
    calender.content = Prompt.inputString("내용? ");
    calender.startDate = Prompt.inputDate("시작일? ");
    calender.endDate = Prompt.inputDate("종료일? ");



    this.calenders[this.size++] = calender;
  }

}
