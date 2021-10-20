package com.studywithus.handler.chargestudy;

import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;

public class MentorApplicantApproveHandler implements Command{

  MemberDao memberDao;

  public MentorApplicantApproveHandler(MemberDao memberDao) {
    this.memberDao = memberDao;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    System.out.println("[멘토 승인 관리]");

    int no = (int) request.getAttribute("applicantNo");

    Member applicant = memberDao.findByNo(no);

    if (applicant == null) {
      System.out.println("해당 정보의 멘토 신청자가 없습니다.");
      return;
    }

    int temp = applicant.getUserAccessLevel();
    temp |= Menu.ACCESS_MENTOR;
    applicant.setUserAccessLevel(temp);

    AuthLogInHandler.userAccessLevel |= Menu.ACCESS_MENTOR;

    System.out.println("멘토 승인이 완료되었습니다.");

  }
}
