package com.studywithus.handler;

import com.studywithus.util.Prompt;

public class MentorHandler {
  public void add() {
    String input = Prompt.inputString("멘토 신청을 하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("취소");
      return;
    }
    System.out.println("멘토 신청이 완료되었습니다.");
  }
}