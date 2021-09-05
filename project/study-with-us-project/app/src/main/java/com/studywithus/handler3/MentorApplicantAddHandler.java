package com.studywithus.handler3;

import java.util.List;
import com.studywithus.domain.ApplicantInfo;
import com.studywithus.domain.Mentor;
import com.studywithus.util.Prompt;

public class MentorApplicantAddHandler extends AbstractMentorApplicantHandler {

  public MentorApplicantAddHandler (List<Mentor> mentorApplicantList) {
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

    Mentor mentorApplicant = new Mentor();

    mentorApplicant.name = ApplicantInfo.getName();
    mentorApplicant.id = ApplicantInfo.getId();

    mentorApplicantList.add(mentorApplicant);

    System.out.println();
    System.out.println("멘토 신청이 완료되었습니다.");
  }
}


