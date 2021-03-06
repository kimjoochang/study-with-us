package com.studywithus.handler.chargestudy;

import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.MentorApplication;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class MentorApplicationAddHandler implements Command {

  MentorApplicationDao mentorApplicationDao;
  SqlSession sqlSession;

  public MentorApplicationAddHandler(MentorApplicationDao mentorApplicationDao, SqlSession sqlSession) {
    this.mentorApplicationDao = mentorApplicationDao;
    this.sqlSession = sqlSession;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[유료 스터디 / 멘토 신청]\n");

    if ((AuthLogInHandler.getLoginUser().getUserAccessLevel() & Menu.ACCESS_MENTOR) == 1) {
      System.out.println("이미 멘토입니다.");
      return;
    }

    MentorApplication mentorApplicantEmail = mentorApplicationDao.findByNo(AuthLogInHandler.loginUser.getNo());

    // 신청서가 이미 있으면서 아직 승인/거절 결정이 안났다면 (visible이 true라면)
    if (mentorApplicantEmail != null && mentorApplicantEmail.getStatus() == 0) {
      System.out.println("이미 멘토 신청이 완료되었습니다.");
      return;
    }

    while (true) {
      String selfIntro = Prompt.inputString("자기 소개를 입력하세요. > ");
      String subject = Prompt.inputString("개설할 스터디 주제를 입력하세요. > ");
      System.out.println();

      if (selfIntro.equals("") || subject.equals("")) {
        System.out.println("모두 필수입력 항목입니다.\n");
        continue;

      } else {
        MentorApplication mentorApplication = new MentorApplication();

        mentorApplication.setNo(AuthLogInHandler.getLoginUser().getNo());
        mentorApplication.setSelfIntroduction(selfIntro);
        mentorApplication.setChargeStudySubject(subject);

        mentorApplicationDao.insert(mentorApplication);
        sqlSession.commit();

        System.out.println();
        System.out.println("멘토 신청이 완료되었습니다.");
      }
      break;
    }
  }
}
