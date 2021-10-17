package com.studywithus.handler.chargestudy;

import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;

public class MentorApplicantApproveHandler implements Command{

  MemberDao memberDao;
  // MentorApplicationDao mentorApplicationDao;

  public MentorApplicantApproveHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
    // this.mentorApplicationDao = mentorApplicationDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[멘토 승인 관리]");

    String email = (String) request.getAttribute("applicantName");

    // *** 질문 ***
    // 멘토신청테이블에서 가져오는 게 아니라 멤버테이블에서 가져오나..? 왜...?

    // requestAgent.request("member.selectOneByEmail", email);
    Member applicant = memberDao.findByEmail(email);

    if (applicant == null) {
      System.out.println("해당 정보의 멘토 신청자가 없습니다.");
      return;
    }

    applicant.setMentor(true);
    AuthLogInHandler.userAccessLevel |= Menu.ACCESS_MENTOR;

    System.out.println("멘토 승인이 완료되었습니다.");

  }
}
