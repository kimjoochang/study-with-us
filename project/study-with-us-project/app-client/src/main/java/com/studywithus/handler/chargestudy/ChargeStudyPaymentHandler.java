package com.studywithus.handler.chargestudy;

import java.sql.Date;
import java.util.List;
import com.studywithus.dao.ChargeStudyDao;
import com.studywithus.dao.PaymentDao;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class ChargeStudyPaymentHandler implements Command {

  PaymentDao paymentDao;
  ChargeStudyDao chargeStudyDao;

  public ChargeStudyPaymentHandler(PaymentDao paymentDao, ChargeStudyDao chargeStudyDao) {
    this.paymentDao = paymentDao;
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기 / 결제]\n");

    int no = (int) request.getAttribute("chargeNo");

    Study chargeStudy = chargeStudyDao.findByNo(no);

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

      paymentDao.insert(payment);

      // 유료스터디 멘티 리스트에 결제한 회원 아이디 추가해서 서버에 저장 요청
      List<String> menteeEmailList = chargeStudy.getMenteeEmailList();
      menteeEmailList.add(AuthLogInHandler.getLoginUser().getEmail());
      chargeStudy.setMenteeEmailList(menteeEmailList);

      chargeStudyDao.update(chargeStudy);

      AuthLogInHandler.userAccessLevel |= Menu.ACCESS_MENTEE;
    }
  }
}
