package com.studywithus.handler.freestudy;

import java.sql.Date;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.MemberDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.util.Prompt;

public class FreeStudyAddHandler implements Command {

  StudyDao freeStudyDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  public FreeStudyAddHandler(StudyDao freeStudyDao, MemberDao memberDao, SqlSession sqlSession) {
    this.freeStudyDao = freeStudyDao;
    this.memberDao = memberDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[무료 스터디 / 생성]\n");

    Study freeStudy = new Study();

    System.out.println("온/오프라인을 선택하세요.");
    System.out.println("1. 온라인");
    System.out.println("2. 오프라인");
    freeStudy.setOnOffLine(Prompt.inputInt("> "));

    if (freeStudy.getOnOffLine() == 2) {
      freeStudy.setArea(Prompt.inputString("지역을 입력하세요. > "));

    } else {
      freeStudy.setArea("온라인");
    }

    freeStudy.setTitle(Prompt.inputString("제목을 입력하세요. > "));
    freeStudy.setWriter(AuthLogInHandler.getLoginUser());
    freeStudy.setContent(Prompt.inputString("설명을 입력하세요. > "));
    freeStudy.setMaxMembers(Prompt.inputInt("모집 인원을 입력하세요. > "));

    while (true) {
      freeStudy.setStartDate(Prompt.inputDate("시작일을 입력하세요. ex) YYYY-MM-DD > "));

      // 현재 날짜 > 시작일인 경우
      if (new Date(System.currentTimeMillis()).compareTo(freeStudy.getStartDate()) == 1) {
        System.out.println("다시 입력하세요.\n");
        continue;

      } else {
        break;
      }
    }

    while (true) {
      freeStudy.setEndDate(Prompt.inputDate("종료일을 입력하세요. ex) YYYY-MM-DD > "));

      // 시작일 < 종료일이 아닌 경우
      if (freeStudy.getEndDate().compareTo(freeStudy.getStartDate()) != 1) {
        System.out.println("종료일은 시작일 이후로 설정하세요.\n");
        continue;

      } else {
        break;
      }
    }

    freeStudyDao.insert(freeStudy);
    Member member = memberDao.findByNo(AuthLogInHandler.getLoginUser().getNo());
    int temp = member.getUserAccessLevel();
    member.setUserAccessLevel(temp);
    memberDao.update(member);
    sqlSession.commit();

    System.out.println();
    System.out.println("무료 스터디 등록이 완료되었습니다.");

  }
}
