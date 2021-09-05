package com.studywithus.handler2;

import java.util.List;
import com.studywithus.domain.Mentor;

public class MentorApplicantListHandler extends AbstractMentorApplicantHandler {

  public MentorApplicantListHandler (List<Mentor> mentorApplicantList) {
    super(mentorApplicantList);
  }
  @Override
  public void execute() {
    System.out.println("[멘토 신청 내역]");

    for(Mentor mentorApplicant : mentorApplicantList) {
      System.out.printf("%s, %s\n",mentorApplicant.name, mentorApplicant.id);
    }
  }
}


