package com.studywithus.handler.chargestudy;

import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.MemberDao;
import com.studywithus.dao.PaymentDao;
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class ChargeStudyPaymentHandler implements Command {

  MemberDao memberDao;
  PaymentDao paymentDao;
  StudyMemberDao studyMemberDao;
  SqlSession sqlSession;

  public ChargeStudyPaymentHandler(MemberDao memberDao, StudyMemberDao studyMemberDao, PaymentDao paymentDao, SqlSession sqlSession) {
    this.memberDao = memberDao;
    this.studyMemberDao = studyMemberDao;
    this.paymentDao = paymentDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 상세보기 / 결제]\n");

    int no = (int) request.getAttribute("chargeNo");

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
      payment.setMentee(AuthLogInHandler.getLoginUser());
      payment.setStudyNo(no);
      payment.setPaymentMethod(0);
      payment.setStatus(0);

      paymentDao.insert(payment);
      studyMemberDao.insert(AuthLogInHandler.getLoginUser().getNo(), no, Study.PARTICIPANT_STATUS);

      AuthLogInHandler.userAccessLevel |= Menu.ACCESS_MENTEE;

      sqlSession.commit();

    }
  }
}
