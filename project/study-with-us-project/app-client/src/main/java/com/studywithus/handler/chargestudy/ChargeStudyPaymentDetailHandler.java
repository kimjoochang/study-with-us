package com.studywithus.handler.chargestudy;

import com.studywithus.dao.PaymentDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyPaymentDetailHandler implements Command {

  PaymentDao paymentDao;
  StudyDao chargeStudyDao;

  public ChargeStudyPaymentDetailHandler(PaymentDao paymentDao, StudyDao chargeStudyDao) {
    this.paymentDao = paymentDao;
    this.chargeStudyDao = chargeStudyDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[마이페이지 / 유료 스터디 결제내역 / 상세보기]\n");
    int no = Prompt.inputInt("번호를 입력하세요. > ");
    Payment payment = paymentDao.findByNo(AuthLogInHandler.getLoginUser().getNo(), no);

    if (payment == null || payment.getStatus()== 1) {
      System.out.println();
      System.out.println("해당 번호의 결제내역이 없습니다.\n");
      return;
    }

    Study study = chargeStudyDao.findByNo(no);

    System.out.printf("결제한 스터디 번호: %s\n", study.getNo());
    System.out.printf("결제한 스터디 제목: %s\n", study.getTitle());
    System.out.printf("결제한 스터디 멘토: %s\n", study.getWriter().getName());
    System.out.printf("결제한 스터디 가격: %s\n", study.getPrice());
    System.out.printf("결제일: %s\n", payment.getPaymentDate());
    System.out.println();


    if (study.getStudyStatus() == 0) {

      System.out.println("1. 결제 취소하기");
      System.out.println("0. 이전");
      int input = Prompt.inputInt("메뉴 번호를 선택하세요. > ");

      if(input == 1) {
        request.setAttribute("chargeNo", no);
        request.getRequestDispatcher("/chargeStudy/paymentCancel").forward(request);

      } else if (input == 0) {
        return;

      } else {
        System.out.println("존재하지 않는 메뉴 번호입니다.");
      }
    } else {
      return;
    }

  }

}

