package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.MentorApplicant;

public abstract class AbstractMentorApplicantHandler implements Command {

  protected List<MentorApplicant> mentorApplicantList;

  public AbstractMentorApplicantHandler(List<MentorApplicant> mentorApplicantList) {
    this.mentorApplicantList = mentorApplicantList;
  }

}
