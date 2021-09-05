package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;

public abstract class AbstractMentorApplicantHandler implements Command {

  protected List<Member> mentorList;

  public AbstractMentorApplicantHandler(List<Member> mentorList) {
    this.mentorList = mentorList;
  }
}
