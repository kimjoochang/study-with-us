package com.studywithus.handler.freestudy;

import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyApplyHandler implements Command {

  StudyDao freeStudyDao;
  SqlSession sqlSession;

  public FreeStudyApplyHandler(StudyDao freeStudyDao, SqlSession sqlSession) {
    this.freeStudyDao = freeStudyDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 상세보기 / 신청]\n");
    int no = (int) request.getAttribute("freeNo");
    Study freeStudy = freeStudyDao.findByNo(no);
    Study paritcipateStudy = freeStudyDao.findByNoParticipateStudy(AuthLogInHandler.getLoginUser().getNo(), no, 1);
    Study applyStudy = freeStudyDao.findByNoApplyStudy(AuthLogInHandler.getLoginUser().getNo(), no);

    if (applyStudy != null || paritcipateStudy != null) {
      System.out.println("이미 신청하신 스터디입니다.");
      return;
    }

    // 모집인원 다 찼을 경우
    if (freeStudy.getMembers().size() == freeStudy.getMaxMembers()) {
      System.out.println("모집 인원이 다 찼습니다.");
      return;
    }

    while (true) {
      String input = Prompt.inputString("무료 스터디를 신청 하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println();
        System.out.println("무료 스터디 신청이 취소되었습니다.");
        return;

      } else if (input.equalsIgnoreCase("y")) {

        freeStudyDao.insertStudyMember(AuthLogInHandler.getLoginUser().getNo(), no, 0);
        sqlSession.commit();

        System.out.println();
        System.out.println("무료 스터디 신청이 완료되었습니다.");
        return;

      } else {
        System.out.println();
        System.out.println("다시 입력하세요.\n");
        continue;
      }
    }
  }
}
