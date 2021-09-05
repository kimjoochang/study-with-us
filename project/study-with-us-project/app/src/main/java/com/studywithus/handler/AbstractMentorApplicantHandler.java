package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;

public abstract class AbstractMentorApplicantHandler implements Command {

  protected List<Member> mentorApplicantList;

  public AbstractMentorApplicantHandler(List<Member> mentorApplicantList) {
    this.mentorApplicantList = mentorApplicantList;
  }

  protected Member findByName(String name) {
    for (Member member : mentorApplicantList) {
      if (member.getName().equals(name)) {
        return member;
      }
    }
    return null;
  }
}
