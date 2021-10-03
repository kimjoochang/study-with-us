package com.studywithus.handler.study;

import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ChargeStudyPaymentCancelHandler implements Command {

  RequestAgent requestAgent;

  // 유료 스터디 결제내역 리스트 (회원 관점)
  List<Payment> chargePaymentList;

  // 각 회원의 참여 유료 스터디 리스트(마이페이지 / 내가 참여한 유료 스터디)
  HashMap<String, List<Study>> participateChargeStudyMap;
  List<Study> participateChargeStudyList;

  public ChargeStudyPaymentCancelHandler(List<Payment> chargePaymentList, List<Member> chargeApplicantList, HashMap<String, List<Study>> participateChargeStudyMap) {
    this.chargePaymentList = chargePaymentList;
    this.participateChargeStudyMap = participateChargeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기 / 결제취소]\n");
    int no = (int) request.getAttribute("chargeNo");

    Study chargeStudy = findByNo(no);


    if (chargeStudy == null) {
      System.out.println("해당 번호의 유료 스터디가 없습니다.");
      return;
    }
    while (true) {
      String input = Prompt.inputString("유료 스터디를 결제를 취소 하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("유료 스터디 결제 취소가 취소되었습니다.");
        return;

      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하시오.\n");
        continue;

      } else {
        System.out.println();
        System.out.println("결제 취소가 완료되었습니다.");
        break;
      }
    }

    List<Member> memberList = chargeStudy.getMembers();
    memberList.remove(AuthLogInHandler.getLoginUser());
    chargeStudy.setMembers(memberList);

    // 유료 스터디 결제내역 리스트 (회원 관점)
    Payment payment = new Payment();
    chargePaymentList.remove(payment);
    AuthLogInHandler.loginUser.setPayment(chargePaymentList);

    // 참여 유료 스터디에 해당 아이디 존재 O
    if (participateChargeStudyMap.containsKey(AuthLogInHandler.getLoginUser().getId())) {
      // 참여 유료 스터디에 아이디 호출 -> 참여 유료 스터디 리스트에 대입
      participateChargeStudyList = participateChargeStudyMap.get(AuthLogInHandler.getLoginUser().getId());

      // 참여 유료 스터디 리스트에 유료 스터디 삭제
      participateChargeStudyList.remove(chargeStudy);
      // 참여 유료 스터디에 아이디 추가
      participateChargeStudyMap.put(AuthLogInHandler.getLoginUser().getId(), participateChargeStudyList);
    } 
    // 유료 스터디 결제한 사람 내역 (멘토 관점)
    chargeApplicantList.remove(AuthLogInHandler.loginUser);
  }
}
