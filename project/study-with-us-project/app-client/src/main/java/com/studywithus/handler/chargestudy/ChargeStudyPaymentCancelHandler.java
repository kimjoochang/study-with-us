package com.studywithus.handler.chargestudy;

import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.PaymentDao;
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Payment;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class ChargeStudyPaymentCancelHandler implements Command {

  PaymentDao paymentDao;
  StudyMemberDao studyMemberDao;
  SqlSession sqlSession;

  public ChargeStudyPaymentCancelHandler(PaymentDao paymentDao, StudyMemberDao studyMemberDao, SqlSession sqlSession) {
    this.paymentDao = paymentDao;
    this.studyMemberDao = studyMemberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기 / 결제취소]\n");

    int no = (int) request.getAttribute("chargeNo");

    Payment payment = paymentDao.findByNo(AuthLogInHandler.getLoginUser().getNo(), no);

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
    paymentDao.delete(AuthLogInHandler.getLoginUser().getNo(), no);
    studyMemberDao.delete(AuthLogInHandler.getLoginUser().getNo(), no);
    sqlSession.commit();

    System.out.println();
    System.out.println("결제 취소가 완료되었습니다.");
  }
}
