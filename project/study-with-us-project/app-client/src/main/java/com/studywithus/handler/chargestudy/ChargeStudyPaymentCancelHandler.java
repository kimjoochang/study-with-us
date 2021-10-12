package com.studywithus.handler.chargestudy;

import java.util.List;
import com.studywithus.dao.ChargeStudyDao;
import com.studywithus.dao.PaymentDao;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyPaymentCancelHandler implements Command {

  PaymentDao paymentDao;
  ChargeStudyDao chargeStudyDao;

  public ChargeStudyPaymentCancelHandler(PaymentDao paymentDao, ChargeStudyDao chargeStudyDao) {
    this.paymentDao = paymentDao;
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기 / 결제취소]\n");

    int no = (int) request.getAttribute("chargeNo");

    Study chargeStudy = chargeStudyDao.findByNo(no);

    Payment payment = paymentDao.findByNo(no, AuthLogInHandler.getLoginUser().getEmail());

    while (true) {
      String input = Prompt.inputString("유료 스터디를 결제를 취소 하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("유료 스터디 결제 취소가 취소되었습니다.");
        return;

      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("유효한 값을 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    List<String> menteeEmailList = chargeStudy.getMenteeEmailList();
    menteeEmailList.remove(AuthLogInHandler.getLoginUser().getEmail());
    chargeStudy.setMenteeEmailList(menteeEmailList);

    chargeStudyDao.update(chargeStudy);

    payment.setVisible(false);
    paymentDao.update(payment);

    System.out.println();
    System.out.println("결제 취소가 완료되었습니다.");
  }
}
