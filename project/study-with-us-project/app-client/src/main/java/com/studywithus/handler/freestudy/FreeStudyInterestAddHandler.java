package com.studywithus.handler.freestudy;

import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.StudyDao;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyInterestAddHandler implements Command {

  StudyDao freeStudyDao;
  SqlSession sqlSession;

  public FreeStudyInterestAddHandler(StudyDao freeStudyDao, SqlSession sqlSession) {
    this.freeStudyDao = freeStudyDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 상세보기 / 관심 목록 / 추가]\n");
    int no = (int) request.getAttribute("freeNo");


    if (freeStudyDao.findByNo(no) == null) {
      System.out.println("해당 번호의 스터디가 없습니다.");
      return;
    }

    while (true) {
      String input = Prompt.inputString("유료 스터디 관심 목록에 추가하시겠습니까? (y/N) ");

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("무료 스터디 관심목록 추가를 취소하였습니다.\n");
        return;

      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {

        freeStudyDao.insertInterest(AuthLogInHandler.getLoginUser().getNo(), no);
        sqlSession.commit();

        System.out.println();
        System.out.println("무료 스터디 관심 목록을 추가하였습니다.\n");
        break;
      }
    }
  }
}
