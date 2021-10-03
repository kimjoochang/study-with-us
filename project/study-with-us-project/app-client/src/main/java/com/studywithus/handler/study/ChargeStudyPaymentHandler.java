package com.studywithus.handler.study;

import java.sql.Date;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.request.RequestAgent;
import com.studywithus.util.Prompt;

public class ChargeStudyPaymentHandler implements Command {

  RequestAgent requestAgent;

  public ChargeStudyPaymentHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  // 유료 스터디 결제내역 리스트 (회원 관점)
  List<Payment> chargePaymentList;

  // 유료 스터디 결제자(멘티) 리스트
  List<Member> members;

  // 내가 참여한 유료 스터디 리스트
  HashMap<String, List<Study>> participateChargeStudyMap;
  List<Study> participateChargeStudyList;

  public ChargeStudyPaymentHandler(List<Payment> chargePaymentList,  List<Member> members, HashMap<String, List<Study>> participateChargeStudyMap) {
    this.chargePaymentList = chargePaymentList;
    this.participateChargeStudyMap = participateChargeStudyMap;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기 / 결제]\n");


    String input = Prompt.inputString("유료 스터디를 결제 하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
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

      for(int i = 0; i < 3; i++) {
        try {
          Thread.sleep(1000);

        } catch (InterruptedException e) {
        }
        System.out.print(heart.append("♡♥"));
      }

      System.out.println();
      System.out.println();
      System.out.println("유료 스터디 결제가 완료 되었습니다.\n");

      Payment payment = new Payment();

      requestAgent.request("chargeStudy.selectOne", request.getAttribute("chargeNo"));

      Study chargeStudy = requestAgent.getObject(Study.class);

      payment.setPaidStudyNo(chargeStudy.getNo());
      payment.setTitle(chargeStudy.getTitle());
      payment.setMentorName(chargeStudy.getWriter().getName());
      payment.setPrice(chargeStudy.getPrice());
      payment.setPaymentDate(new Date(System.currentTimeMillis()));

      // 결제 수단 미구현
      // payment.setPaymentMethod(payment.getPaymentMethod());

      requestAgent.request("payment.insert", payment);

      AuthLogInHandler.userAccessLevel |= Menu.ACCESS_MENTEE;
      //      SendPaymentSms.execute(chargeStudy);

      // 멘티 리스트 //**타입?
      List<Member> memberList = chargeStudy.getMembers();
      memberList.add(AuthLogInHandler.getLoginUser().getEmail());
      chargeStudy.setMembers(memberList);

      // 유료 스터디 결제내역 리스트 (회원 관점)
      Payment payment = new Payment();
      chargePaymentList.add(payment);
      AuthLogInHandler.loginUser.setPayment(chargePaymentList);

      // 참여 유료 스터디에 해당 아이디 존재 O
      if (participateChargeStudyMap.containsKey(AuthLogInHandler.getLoginUser().getEmail())) {
        // 참여 유료 스터디에 아이디 호출 -> 참여 유료 스터디 리스트에 대입
        participateChargeStudyList = participateChargeStudyMap.get(AuthLogInHandler.getLoginUser().getEmail());

        // 참여 유료 스터디 리스트에 유료 스터디 추가
        participateChargeStudyList.add(chargeStudy);
        // 참여 유료 스터디에 아이디 추가
        participateChargeStudyMap.put(AuthLogInHandler.getLoginUser().getEmail(), participateChargeStudyList);

        // 참여 유료 스터디에 해당 아이디 존재 X
      } else {
        // 새로운 참여 유료 스터디 리스트 생성
        participateChargeStudyList = new ArrayList<>();

        // 참여 유료 스터디 리스트에 유료 스터디 추가
        participateChargeStudyList.add(chargeStudy);
        // 참여 유료 스터디에 아이디 추가
        participateChargeStudyMap.put(AuthLogInHandler.getLoginUser().getEmail(), participateChargeStudyList);
      }

      // 유료 스터디 결제한 사람 내역 (멘토 관점)
      members.add(AuthLogInHandler.loginUser);
    }
    return;
  }
}
