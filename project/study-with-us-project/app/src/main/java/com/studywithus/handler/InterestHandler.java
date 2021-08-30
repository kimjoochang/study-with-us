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
  public void freeList() {
    System.out.println("[관심목록 조회]");
    for (int i = 0; i < this.size; i++) {
      if(this.interests[i].freeInterest.onOffLine.equals("2")) {
        System.out.println();
        System.out.printf("%d, %s, %s, %d, %s\n",
            this.interests[i].freeInterest.no,
            this.interests[i].freeInterest.title,
            this.interests[i].freeInterest.writer,
            this.interests[i].freeInterest.onOffLine,
            this.interests[i].freeInterest.area
            );
      }else {
        System.out.println();
        System.out.printf("%d, %s, %s, %d \n",
            this.interests[i].freeInterest.no,
            this.interests[i].freeInterest.title,
            this.interests[i].freeInterest.writer,
            this.interests[i].freeInterest.onOffLine);
      }
    }
  }

  public void chargeList() {
    System.out.println("[관심목록 조회]");
    for (int i = 0; i < this.size; i++) {
      System.out.printf("%d, %s, %s, %d,\n",
          this.interests[i].chargeInterest.no,
          this.interests[i].chargeInterest.title,
          this.interests[i].chargeInterest.writer,
          this.interests[i].chargeInterest.price);
    }
  }

  // 무료 스터디 관심 목록 삭제
  public void freeDelete() {
    System.out.println("[관심 목록 / 무료 스터디]");
    int no = Prompt.inputInt("번호? ");

    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 무료 스터디 관심 목록이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록을 취소하였습니다.");
      return;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.interests[i - 1] = this.interests[i];
    }
    this.interests[--this.size] = null;

    System.out.println("무료 스터디 관심 목록을 삭제하였습니다.");
  }

  // 유료 스터디 관심 목록 삭제
  public void chargeDelete() {
    System.out.println("[관심 목록 / 유료 스터디]");
    int no = Prompt.inputInt("번호? ");

    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 유료 스터디 관심 목록이 없습니다.");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("유료 스터디 관심 목록을 취소하였습니다.");
      return;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.interests[i - 1] = this.interests[i];
    }
    this.interests[--this.size] = null;

    System.out.println("유료 스터디 관심 목록을 삭제하였습니다.");
  }

  // 유/무료 스터디 조회
  private int indexOf(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.interests[i].no == no) {
        return i;
      }
    }
    return -1;
  }
}
