package com.studywithus.handler.freestudy;

import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyUpdateHandler implements Command {

  StudyDao freeStudyDao;
  SqlSession sqlSession;

  public FreeStudyUpdateHandler(StudyDao freeStudyDao, SqlSession sqlSession) {
    this.freeStudyDao = freeStudyDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 수정]\n");
    int no = (int) request.getAttribute("freeNo");

    Study freeStudy = freeStudyDao.findByNo(no);

    if (freeStudy == null) {
      System.out.println();
      System.out.println("해당 번호의 무료 스터디가 없습니다.\n");
      return;
    }

    if (freeStudy.getWriter().getNo() != AuthLogInHandler.getLoginUser().getNo()) {
      System.out.println("변경 권한이 없습니다.");
      return;
    }

    String title = Prompt.inputString(String.format("[%s] 수정할 제목: ", freeStudy.getTitle()));
    String content = Prompt.inputString(String.format("[%s] 수정할 설명: ", freeStudy.getContent()));
    System.out.println();

    while (true) {
      String input = Prompt.inputString("정말 수정하시겠습니까? (y/N) ");
      System.out.println();

      if (input.equalsIgnoreCase("n") || input.length() == 0) {
        System.out.println("무료 스터디 수정을 취소하였습니다.");
        return;

      } else if (!input.equalsIgnoreCase("y")) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    freeStudy.setTitle(title);
    freeStudy.setContent(content);

    freeStudyDao.update(freeStudy);
    sqlSession.commit();

    System.out.println("무료 스터디를 수정하였습니다.");
  }
}
