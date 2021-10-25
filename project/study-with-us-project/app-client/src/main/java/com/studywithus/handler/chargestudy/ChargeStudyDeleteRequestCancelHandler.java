package com.studywithus.handler.chargestudy;

import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.util.Prompt;

public class ChargeStudyDeleteRequestCancelHandler implements Command {

  StudyDao chargeStudyDao;
  SqlSession sqlSession;

  public ChargeStudyDeleteRequestCancelHandler(StudyDao chargeStudyDao, SqlSession sqlSession)  {
    this.chargeStudyDao = chargeStudyDao;
    this.sqlSession= sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 삭제 요청 취소]\n");
    int no = (int) request.getAttribute("chargeNo");

    Study chargeStudy = chargeStudyDao.findByNo(no);

    String input = Prompt.inputString("정말 삭제 요청을 취소 하시겠습니까? (y/N) ");
    System.out.println();
    while (true) {
      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("유료 스터디 삭제 요청 취소를 취소하였습니다.\n");
        return;
      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하세요.\n");
        continue;
      } else {
        break;
      }
    }
    chargeStudy.setDeleteStatus(0);

    chargeStudyDao.update(chargeStudy);
    sqlSession.commit();

    System.out.println("삭제 요청 취소가 완료되었습니다.\n");
  }
}
