package com.studywithus.handler.study;

import com.studywithus.domain.Member;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.handler.user.AuthLogInHandler;
import com.studywithus.menu.Menu;
import com.studywithus.request.RequestAgent;

public class MentorApplicantApproveHandler implements Command{

  RequestAgent requestAgent;

  public MentorApplicantApproveHandler(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void execute(CommandRequest request) throws Exception {
    String email = (String) request.getAttribute("applicantName");

    requestAgent.request("member.selectOneByEmail", email);

    Member Applicant = requestAgent.getObject(Member.class);

    Applicant.setMentor(true);

    AuthLogInHandler.userAccessLevel |= Menu.ACCESS_MENTOR;

    System.out.println("멘토 승인이 완료되었습니다.");

  }

}
