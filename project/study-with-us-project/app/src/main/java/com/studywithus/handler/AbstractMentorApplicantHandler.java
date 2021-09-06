package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Mentor;

public abstract class AbstractMentorApplicantHandler implements Command {

  protected List<Member> mentorApplicantList;
  protected List<Mentor> mentorList;

  public AbstractMentorApplicantHandler(List<Member> mentorApplicantList) {
    this.mentorApplicantList = mentorApplicantList;
  }
  public AbstractMentorApplicantHandler(List<Member> mentorApplicantList, List<Mentor> mentorList) {
    this.mentorApplicantList = mentorApplicantList;
    this.mentorList = mentorList;
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
