package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class StudyDetailHandler extends AbstractStudyHandler {

  Study freeStudy;
  Study chargeStudy;
  Payment payment;

  List<Study> freeInterestList;
  List<Study> chargeInterestList;
  List<Study> applicationList;
  List<Payment> paymentList;

  public StudyDetailHandler(List<Study> freeStudyList, List<Study> chargeStudyList, List<Study> freeInterestList, List<Study> chargeInterestList, List<Study> applicationList, List<Payment> paymentList) {
    super(freeStudyList, chargeStudyList);
    this.freeInterestList = freeInterestList;
    this.chargeInterestList = chargeInterestList;
    this.applicationList = applicationList;
    this.paymentList = paymentList;
  }

  public void execute() {

    // 무료 스터디 상세보기
    if (Study.value) {
      System.out.println("[무료 스터디 / 상세보기]\n");

      int no = Prompt.inputInt("번호? ");
      freeStudy = findByNo(no);

      if (freeStudy == null) {
        System.out.println();
        System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
        return;
      }

      System.out.printf("제목: %s\n", freeStudy.getTitle());
      System.out.printf("팀장: %s\n", freeStudy.getWriter().getName());

      if (freeStudy.getArea() != null) {
        System.out.printf("지역: %s\n", freeStudy.getArea());
      }

      System.out.printf("설명: %s\n", freeStudy.getExplanation());
      System.out.printf("규칙: %s\n", freeStudy.getRule());
      System.out.printf("등록일: %s\n", freeStudy.getRegisteredDate());

      freeStudy.setViewCount(freeStudy.getViewCount() + 1);
      System.out.printf("조회수: %d\n", freeStudy.getViewCount());

      System.out.println();

      System.out.println("1. 신청");
      System.out.println("2. 관심목록 추가");
      System.out.println("0. 이전 메뉴");

      int input = Prompt.inputInt("선택 > ");

      switch (input) {
        case 1: apply(); break;
        case 2: interest(); break;
        default: return;
      }
    }

    // 유료 스터디 상세보기
    else {
      System.out.println("[유료 스터디 / 상세보기]\n");
      int no = Prompt.inputInt("번호? ");

      chargeStudy = findByNo(no);

      if (chargeStudy == null) {
        System.out.println();
        System.out.println("해당 번호의 유료 스터디가 없습니다.\n");
        return;
      }

      System.out.printf("제목: %s\n", chargeStudy.getTitle());
      System.out.printf("설명: %s\n", chargeStudy.getExplanation());
      System.out.printf("지역: %s\n", chargeStudy.getArea());
      System.out.printf("멘토: %s\n", chargeStudy.getWriter());
      System.out.printf("가격: %s\n", chargeStudy.getPrice());
      System.out.printf("등록일: %s\n", chargeStudy.getRegisteredDate());

      chargeStudy.setViewCount(chargeStudy.getViewCount() + 1);
      System.out.printf("조회수: %d\n", chargeStudy.getViewCount());

      System.out.println();
      System.out.println("1. 결제하기");

      for (Study nowStudy : chargeInterestList) {
        if (!chargeStudy.equals(nowStudy)) {
          no = 0;
          break;
        }
        no = 1;
      }

      if (no == 0) {
        System.out.println("2. 관심목록 삭제하기");

      } else if (no == 1) {
        System.out.println("3. 관심목록 추가하기");
      }

      System.out.println("0. 이전\n");

      while(true) {
        int input = Prompt.inputInt("선택>");

        if (input == 1) {
          payHandler();
        } else if (input == 2) {
          if (no == 0) {
            interestDeleteHandler(chargeStudy);
            return;
          }
          interestAddHandler(chargeStudy);

        } else if (input == 0) {
          return;

        } else {
          System.out.println("잘못된 번호입니다.");
          continue;
        }
        return;
      }
    }
  }

  // 무료 스터디 신청
  public void apply() {
    System.out.println("[무료 스터디 / 상세보기 / 신청]\n");

    String input = Prompt.inputString("무료 스터디를 신청 하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 신청이 취소되었습니다.");
      return;
    }

    // [수정] 타입 불일치
    freeStudy.setWriter(AuthLoginHandler.getLoginUser());

    applicationList.add(freeStudy);

    System.out.println();
    System.out.println("무료 스터디 신청이 완료되었습니다.\n");
  }

  // 무료 스터디 관심목록 추가
  public void interest() {
    System.out.println("[무료 스터디 / 상세보기 / 관심 목록]\n");

    String input = Prompt.inputString("무료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("무료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }

    freeInterestList.add(freeStudy);

    System.out.println();
    System.out.println("무료 스터디 관심 목록에 추가되었습니다.\n");
  }

  // 유료 스터디 결제
  private void payHandler() {
    System.out.println("[유료 스터디 / 결제]\n");

    String input = Prompt.inputString("유료 스터디를 결제 하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println(" 유료 스터디 결제를 취소하셨습니다.");

    } else {
      StringBuffer heart = new StringBuffer("");

      System.out.print("------------------------------------");
      System.out.println("\n"
          + "(＼(＼     \n"
          + "(  -.- )~♥\n"
          + " O_(\")(\")");
      System.out.println("------------------------------------");
      System.out.print("결제중");

      for(int i = 0; i < 5; i++) {
        try {
          Thread.sleep(1000);
        } catch (InterruptedException e) {

        }
        System.out.print(heart.append("♡♥"));
      }

      System.out.println();
      System.out.println();
      System.out.println("유료 스터디 결제가 완료 되었습니다.\n");

      // [수정] 타입 불일치
      paymentList.add(payment);
    }
    return;
  }

  // 유료 스터디 관심목록 추가
  private void interestAddHandler(Study chargeStudy) {

    for (Study chargeInterest : chargeInterestList) {
      if (chargeStudy.equals(chargeInterest)) {
        System.out.println("");
      }
    }

    String input = Prompt.inputString("관심 목록에 추가하시겠습니까? (y/N)");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("유료 스터디 관심 목록 추가를 취소하였습니다.\n");
      return;
    }

    chargeInterestList.add(chargeStudy);

    System.out.println();
    System.out.println("유료 스터디 관심 목록에 추가되었습니다.\n");
  }

  // 유료 스터디 관심목록 삭제
  private void interestDeleteHandler(Study chargeStudy) {
    String input = Prompt.inputString("정말 삭제하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("유료 스터디 관심 목록을 취소하였습니다.\n");
      return;
    }

    chargeInterestList.remove(chargeStudy);

    System.out.println();
    System.out.println("유료 스터디 관심 목록을 삭제하였습니다.\n");
  }
}
