package com.studywithus.handler;

import com.studywithus.domain.ChargeStudy;
import com.studywithus.domain.FreeStudy;
import com.studywithus.domain.InterestList;
import com.studywithus.util.Prompt;

public class InterestHandler {

  static final int MAX_LENGTH = 5;

  static InterestList[] interests = new InterestList[MAX_LENGTH];
  static int size = 0;

  // 무료 스터디 관심 목록 추가
  public void freeAdd(FreeStudy study) {
    String input = Prompt.inputString("무료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }

    InterestList interest = new InterestList();

    interest.setFreeInterest(study);

    this.interests[this.size++] = interest;
    System.out.println("무료 스터디 관심 목록에 추가되었습니다.\n");
  }

  // 유료 스터디 관심 목록 추가
  public static void chargeAdd(ChargeStudy study) {
    String input = Prompt.inputString("관심 목록에 추가하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("유료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }

    InterestList interest = new InterestList();


    interest.setChargeInterest(study);

    interests[size++] = interest;
    System.out.println("유료 스터디 관심 목록에 추가되었습니다.\n");
    return;
  }

  // 무료 스터디 관심 목록 조회
  public void freeList() {
    System.out.println("[무료 스터디 / 관심 목록 / 조회]");

    for (int i = 0; i < this.size; i++) {
      if(this.interests[i].getFreeInterest().getOnOffLine().equals("2")) {
        System.out.println();

        System.out.printf("%d, %s, %s, %d, %s\n",
            this.interests[i].getFreeInterest().getNo(),
            this.interests[i].getFreeInterest().getTitle(),
            this.interests[i].getFreeInterest().getWriter(),
            this.interests[i].getFreeInterest().getOnOffLine(),
            this.interests[i].getFreeInterest().getArea());

      } else {
        System.out.println();

        System.out.printf("%d, %s, %s, %d \n",
            this.interests[i].getFreeInterest().getNo(),
            this.interests[i].getFreeInterest().getTitle(),
            this.interests[i].getFreeInterest().getWriter(),
            this.interests[i].getFreeInterest().getOnOffLine());
      }
    }
  }

  // 유료 스터디 관심 목록 조회
  public void chargeList() {
    System.out.println("[유료 스터디 / 관심 목록 /  조회]");

    for (int i = 0; i < this.size; i++) {
      System.out.printf("%d, %s, %s, %d \n",
          this.interests[i].getChargeInterest().getNo(),
          this.interests[i].getChargeInterest().getTitle(),
          this.interests[i].getChargeInterest().getWriter(),
          this.interests[i].getChargeInterest().getPrice());
    }
  }

  // 무료 스터디 관심 목록 삭제
  public void freeDelete() {
    System.out.println("[무료 스터디 / 관심 목록 / 삭제]\n");
    int no = Prompt.inputInt("번호? ");

    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 무료 스터디 관심 목록이 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.interests[i - 1] = this.interests[i];
    }
    this.interests[--this.size] = null;

    System.out.println("무료 스터디 관심 목록을 삭제하였습니다.\n");
  }

  // 유료 스터디 관심 목록 삭제
  public void chargeDelete() {
    System.out.println("[유료 스터디 / 관심 목록 / 삭제]\n");
    int no = Prompt.inputInt("번호? ") - 1;

    int index = indexOf(no);

    if (index == -1) {
      System.out.println("해당 번호의 유료 스터디 관심 목록이 없습니다.\n");
      return;
    }

    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");
    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("유료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    for (int i = index + 1; i < this.size; i++) {
      this.interests[i - 1] = this.interests[i];
    }
    this.interests[--this.size] = null;

    System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");
  }

  // 유/무료 스터디 조회
  private int indexOf(int no) {
    for (int i = 0; i < this.size; i++) {
      if (this.interests[i].getNo() == no) {
        return i;
      }
    }
    return -1;
  }
}
