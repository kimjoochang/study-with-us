package com.studywithus.handler;

import com.studywithus.domain.LoginMemberInfo;
import com.studywithus.domain.NewMember;
import com.studywithus.util.Prompt;

public class MentorApplicantHandler {

  NewMember[] MentorApplicants = new NewMember[5];
  int size = 0;

  // 멘토 신청하기
  public void add() {
    String input = Prompt.inputString("멘토 신청을 하시겠습니까? (y/N) ");

    if (input.equalsIgnoreCase("n") || input.length() == 0) {
      System.out.println("취소");
      return;
    }
    MentorApplicants[size].setId(LoginMemberInfo.getId());
    MentorApplicants[size].setName(LoginMemberInfo.getName());
    size++;
    System.out.println();
    System.out.println("멘토 신청이 완료되었습니다.");
  }
  // 멘토 신청내역 조회하기
  public void list() {
    System.out.println("[멘토 신청 내역]");

    for(int i = 0; i < this.size; i++) {
      System.out.printf("%s, %s\n",this.MentorApplicants[i].getName(), this.MentorApplicants[i].getName());
    }
  }
}
