package com.studywithus.handler;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.domain.FreeStudy;
import com.studywithus.domain.InterestList;
import com.studywithus.util.Prompt;

public class InterestHandler {
  static final int MAX_LENGTH = 5;

  InterestList[] interests = new InterestList[MAX_LENGTH];
  int size = 0;

  public void freeAdd(FreeStudy study) {
    String input = Prompt.inputString("관심목록에 추가하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("취소");
      return;
    }
    InterestList interest = new InterestList();

    interest.freeInterest = study;

    this.interests[this.size++] = interest;
    System.out.println("관심목록에 추가되었습니다.");
  }


  public void chargeAdd(ChargeStudy study) {
    String input = Prompt.inputString("관심목록에 추가하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("취소");
      return;
    }
    InterestList interest = new InterestList();

    interest.chargeInterest = study;

    this.interests[this.size++] = interest;
    System.out.println("관심목록에 추가되었습니다.");
  }
  public void list() {
    System.out.println("[관심목록 조회]");
    for (int i = 0; i < this.size; i++) {
      System.out.printf("%d, %s, %s, %d,\n",
          this.interests[i].chargeInterest.no,
          this.interests[i].chargeInterest.title,
          this.interests[i].chargeInterest.writer,
          this.interests[i].chargeInterest.price);
    }
  }
}
