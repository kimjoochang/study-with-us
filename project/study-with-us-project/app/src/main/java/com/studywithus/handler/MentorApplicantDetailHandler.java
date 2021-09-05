package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.util.Prompt;

public class MentorApplicantDetailHandler extends AbstractMentorApplicantHandler {

  public MentorApplicantDetailHandler(List<Member> mentorApplicantList) {
    super(mentorApplicantList);
  }

  @Override
  public void execute() {
    System.out.println("[커뮤니티 / 상세보기] \n");

    String name = Prompt.inputString("이름? ");
    Member member = findByName(name);

    if (member == null) {
      System.out.println();
      System.out.println("입력하신 이름과 일치하는 신청내역이 없습니다.\n");
      return;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("아이디: %s\n", member.getId());
  }


}
