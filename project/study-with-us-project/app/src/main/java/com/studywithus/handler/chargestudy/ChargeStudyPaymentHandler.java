package com.studywithus.handler;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.util.Prompt;

public class ChargeStudyPaymentHandler extends AbstractStudyHandler {

  Study chargeStudy;

  // 유료 스터디 결제내역 리스트 (회원 관점)
  List<Payment> chargePaymentList;

  // 유료 스터디 결제자 내역 (멘토 관점)
  List<Member> chargeApplicantList;

  // 각 회원의 참여 유료 스터디 리스트
  HashMap<String, List<Study>> participateChargeStudyMap;
  List<Study> participateChargeStudyList;

  public ChargeStudyPaymentHandler(List<Study> chargeStudyList, List<Payment> chargePaymentList, List<Member> chargeApplicantList, HashMap<String, List<Study>> participateChargeStudyMap) {
    super(chargeStudyList);
    this.chargePaymentList = chargePaymentList;
    this.chargeApplicantList = chargeApplicantList;
    this.participateChargeStudyMap = participateChargeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[유료 스터디 / 결제]\n");

    String input1 = Prompt.inputString("유료 스터디를 결제 하시겠습니까? (y/N) ");

    if (input1.equalsIgnoreCase("n") || input1.length() == 0) {
      System.out.println(" 유료 스터디 결제를 취소하셨습니다.");
    }

    else {
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

      // 유료 스터디 결제내역 리스트 (회원 관점)
      Payment payment = new Payment();
      chargePaymentList.add(payment);
      AuthLogInHandler.loginUser.setPayment(chargePaymentList);

      // 참여 유료 스터디에 해당 아이디 존재 O
      if (participateChargeStudyMap.containsKey(AuthLogInHandler.getLoginUser().getId())) {
        // 참여 유료 스터디에 아이디 호출 -> 참여 유료 스터디 리스트에 대입
        participateChargeStudyList = participateChargeStudyMap.get(AuthLogInHandler.getLoginUser().getId());

        // 참여 유료 스터디 리스트에 유료 스터디 추가
        participateChargeStudyList.add(chargeStudy);
        // 참여 유료 스터디에 아이디 추가
        participateChargeStudyMap.put(AuthLogInHandler.getLoginUser().getId(), participateChargeStudyList);

        // 참여 유료 스터디에 해당 아이디 존재 X
      } else {
        // 새로운 참여 유료 스터디 리스트 생성
        participateChargeStudyList = new ArrayList<>();

        // 참여 유료 스터디 리스트에 유료 스터디 추가
        participateChargeStudyList.add(chargeStudy);
        // 참여 유료 스터디에 아이디 추가
        participateChargeStudyMap.put(AuthLogInHandler.getLoginUser().getId(), participateChargeStudyList);
      }

      // 유료 스터디 결제한 사람 내역 (멘토 관점)
      chargeApplicantList.add(AuthLogInHandler.loginUser);
    }
    return;
  }
}
