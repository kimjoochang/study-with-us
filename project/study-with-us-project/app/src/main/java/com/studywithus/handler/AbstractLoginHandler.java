package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;

public abstract class AbstractLoginHandler implements Command {

  protected List<Member> memberList;

  public AbstractLoginHandler(List<Member> memberList) {
    this.memberList = memberList;
  }
}
