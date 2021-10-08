package com.studywithus.handler.chargestudy;

import java.sql.Date;
import java.util.HashMap;
import java.util.List;
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

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기 / 결제]\n");

    int no = (int) request.getAttribute("chargeNo");

    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("chargeStudy.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      System.out.println(requestAgent.getObject(String.class));
      return;
    }

    Study chargeStudy = requestAgent.getObject(Study.class);


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

      // 결제내역 생성해서 서버에 저장 요청
      Payment payment = new Payment();
      payment.setMenteeEmail(AuthLogInHandler.getLoginUser().getEmail());
      payment.setPaidStudyNo(chargeStudy.getNo());
      payment.setTitle(chargeStudy.getTitle());
      payment.setMentorName(chargeStudy.getWriter().getName());
      payment.setPrice(chargeStudy.getPrice());
      payment.setPaymentDate(new Date(System.currentTimeMillis()));
      payment.setVisible(true);

      requestAgent.request("payment.insert", payment);

      // 유료스터디 멘티 리스트에 결제한 회원 아이디 추가해서 서버에 저장 요청
      List<String> menteeEmailList = chargeStudy.getMenteeEmailList();
      menteeEmailList.add(AuthLogInHandler.getLoginUser().getEmail());
      chargeStudy.setMenteeEmailList(menteeEmailList);

      requestAgent.request("chargeStudy.update", chargeStudy);

      AuthLogInHandler.userAccessLevel |= Menu.ACCESS_MENTEE;
    }
  }
}
