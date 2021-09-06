package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.util.Prompt;

public class MentorApplicantAddHandler extends AbstractMentorApplicantHandler {

  public MentorApplicantAddHandler (List<Member> mentorApplicantList) {
    super(mentorApplicantList);
  }

  // 멘토 신청하기
  @Override
  public void execute() {
    String input = Prompt.inputString("멘토 신청을 하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("취소");
      return;
    }

    Member mentorApplicant = new Member();

    mentorApplicant.setId(AuthLoginHandler.loginUser.getId());
    mentorApplicant.setName(AuthLoginHandler.loginUser.getName());

    mentorApplicantList.add(mentorApplicant);

    System.out.println();
    System.out.println("멘토 신청이 완료되었습니다.");
  }
}
