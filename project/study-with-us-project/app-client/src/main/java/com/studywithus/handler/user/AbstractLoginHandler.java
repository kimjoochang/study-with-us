package com.studywithus.handler.user;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.handler.Command;

public abstract class AbstractLoginHandler implements Command {

  protected List<Member> memberList;

  public AbstractLoginHandler(List<Member> memberList) {
    this.memberList = memberList;
  }
}
