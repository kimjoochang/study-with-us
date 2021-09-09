package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Mentor;

public abstract class AbstractMentorHandler implements Command {

  protected List<Mentor> mentorList;

  public AbstractMentorHandler(List<Mentor> mentorList) {
    this.mentorList = mentorList;
  }
}
