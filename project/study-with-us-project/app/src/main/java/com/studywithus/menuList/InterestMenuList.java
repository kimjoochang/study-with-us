package com.studywithus.menuList;

import java.util.List;
import com.studywithus.domain.ChargeStudy;
import com.studywithus.domain.FreeStudy;
import com.studywithus.handler.InterestChargeDeleteHandler;
import com.studywithus.handler.InterestChargeListHandler;
import com.studywithus.handler.InterestFreeDeleteHandler;
import com.studywithus.handler.InterestFreeListHandler;
import com.studywithus.util.Prompt;


public class InterestMenuList {

  List<FreeStudy> freeInterestList;
  List<ChargeStudy> chargeInterestList;

  InterestFreeListHandler interestFreeListHandler = new InterestFreeListHandler(freeInterestList);
  InterestFreeDeleteHandler interestFreeDeleteHandler = new InterestFreeDeleteHandler(freeInterestList);

  InterestChargeListHandler interestChargeListHandler = new InterestChargeListHandler(chargeInterestList);
  InterestChargeDeleteHandler interestChargeDeleteHandler = new InterestChargeDeleteHandler(chargeInterestList);

  int input;

  public void interestMenuList() {
    while (true) {
      System.out.println("[관심목록]\n");
      System.out.println("1. 무료 스터디 관심목록");
      System.out.println("2. 유료 스터디 관심목록");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        freeInterestMenuList();

      } else if (input == 2) {
        chargeInterestMenuList();

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
    } 
  }

  private void freeInterestMenuList() {
    while (true) {
      System.out.println("[무료 스터디 / 관심목록]\n");
      System.out.println("1. 무료 스터디 관심목록 조회");
      System.out.println("2. 무료 스터디 관심목록 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        interestFreeListHandler.execute();

      } else if (input == 2) {
        interestFreeDeleteHandler.execute();
        // 삭제
      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
    }
  }

  private boolean chargeInterestMenuList() {
    while(true) {
      System.out.println("[유료 스터디 / 관심목록]\n");
      System.out.println("1. 유료 스터디 관심목록 조회");
      System.out.println("2. 유료 스터디 관심목록 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if(input == 1) {
        interestChargeListHandler.execute();

      } else if(input == 2) {
        interestChargeDeleteHandler.execute();
        // 삭제
      } else if (input == 0) {
        return false;
      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
    }
  }
}

