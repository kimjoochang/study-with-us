package com.studywithus.handler2;

import java.util.List;
import com.studywithus.domain.Mentor;

public abstract class AbstractMentorApplicantHandler implements Command {

  protected List<Mentor> mentorApplicantList;

  public AbstractMentorApplicantHandler(List<Mentor> mentorApplicantList) {
    this.mentorApplicantList = mentorApplicantList;
  }

}
