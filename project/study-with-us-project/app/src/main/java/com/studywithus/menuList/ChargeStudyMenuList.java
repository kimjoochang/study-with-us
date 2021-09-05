package com.studywithus.menuList;

import java.util.ArrayList;
import java.util.List;
import com.studywithus.domain.ChargeStudy;
import com.studywithus.domain.FreeStudy;
import com.studywithus.handler.ChargeInterestAddHandler;
import com.studywithus.handler.ChargeInterestDeleteHandler;
import com.studywithus.handler.ChargeInterestListHandler;
import com.studywithus.handler.ChargeStudyAddHandler;
import com.studywithus.handler.ChargeStudyDeleteHandler;
import com.studywithus.handler.ChargeStudyDeletedDetailHandler;
import com.studywithus.handler.ChargeStudyDetailHandler;
import com.studywithus.handler.ChargeStudyListHandler;
import com.studywithus.handler.ChargeStudyUpdateHandler;
import com.studywithus.handler.FreeInterestDeleteHandler;
import com.studywithus.handler.FreeInterestHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyMenuList {

  List<ChargeStudy> chargeStudyList = new ArrayList<>();

  List<FreeStudy> freeInterestList;
  List<ChargeStudy> chargeInterestList;

  FreeInterestHandler interestFreeListHandler = new FreeInterestHandler(freeInterestList);
  FreeInterestDeleteHandler interestFreeDeleteHandler = new FreeInterestDeleteHandler(freeInterestList);

  ChargeInterestAddHandler chargeinterestAddHandler = new ChargeInterestAddHandler(chargeInterestList);
  ChargeInterestListHandler chargeinterestListHandler = new ChargeInterestListHandler(chargeInterestList);
  ChargeInterestDeleteHandler chargeinterestDeleteHandler = new ChargeInterestDeleteHandler(chargeInterestList);

  ChargeStudyAddHandler chargeStudyAddHandler = new ChargeStudyAddHandler(chargeStudyList);
  ChargeStudyListHandler chargeStudyListHandler = new ChargeStudyListHandler(chargeStudyList);
  ChargeStudyDeleteHandler chargeStudyDeleteHandler = new ChargeStudyDeleteHandler(chargeStudyList);
  ChargeStudyDeletedDetailHandler chargeStudyDeletedDetailHandler = new ChargeStudyDeletedDetailHandler(chargeStudyList);
  ChargeStudyDetailHandler chargeStudyDetailHandler = new ChargeStudyDetailHandler(chargeStudyList);
  ChargeStudyUpdateHandler chargeStudyUpdateHandler = new ChargeStudyUpdateHandler(chargeStudyList);

  int input;

  // 유료 스터디 메뉴 조회
  public void chargeStudyMenuList() {
    while(true) {

      System.out.println("[유료 스터디]\n");
      System.out.println("1. 생성");
      System.out.println("2. 조회");
      System.out.println("3. 상세보기");
      System.out.println("4. 변경");
      System.out.println("5. 삭제");
      System.out.println("0. 이전\n");

      input = Prompt.inputInt("메뉴를 선택해주세요. > ");
      System.out.println();

      if (input == 1) {
        chargeStudyAddHandler.execute();

      } else if (input == 2) {
        chargeStudyListHandler.execute();

      } else if (input == 3) {
        chargeStudyDetailHandler.execute();

      } else if (input == 4) {
        chargeStudyUpdateHandler.execute();

      } else if (input == 5) {
        chargeStudyDeleteHandler.execute();

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      continue;
    }		

  }

  // 유료 스터디 상세보기
  public  int detailMenuList(int input, ChargeStudy study) {

    while(true) {
      if (input == 1) {
        System.out.print("[유료 스터디 / 결제]");

        String input1 = Prompt.inputString("유료 스터디를 결제 하시겠습니까? (y/N)");
        if (input1.equalsIgnoreCase("n") || input1.length() == 0) {
          System.out.println(" 유료 스터디 결제를 취소하셨습니다.");
          continue;
        }
        else {
          StringBuffer dot = new StringBuffer("");

          System.out.print("------------------------------------");
          System.out.println("\n"
              + "(＼(＼     \n"
              + "(  -.- )~♥\n"
              + " O_(\")(\")");
          System.out.println("------------------------------------");
          System.out.print("결제중");
          for(int i = 0; i < 3; i++) {
            try {
              Thread.sleep(1000);
            } catch (InterruptedException e) {
            }
            System.out.print(dot.append(".."));
          }
          System.out.println();
          System.out.println("유료 스터디 결제가 완료 되었습니다.\n");
        }
        return 1;
      } else if (input == 2) {
        chargeinterestAddHandler.execute(study);
        return 1;
      }
    }
  }

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
        chargeInterestListHandler.execute();

      } else if(input == 2) {
        chargeInterestDeleteHandler.execute();
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