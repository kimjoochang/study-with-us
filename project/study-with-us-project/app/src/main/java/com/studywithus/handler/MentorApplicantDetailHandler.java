package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.util.Prompt;

public class MentorApplicantDetailHandler extends AbstractMentorApplicantHandler {

  public MentorApplicantDetailHandler(List<Member> mentorApplicantList) {
    super(mentorApplicantList);
  }

  public Member execute1() {
    System.out.println("[멘토 신청 내역 / 상세보기] \n");

    String name = Prompt.inputString("이름? ");
    Member member = findByName(name);

    if (member == null) {
      System.out.println();
      System.out.println("입력하신 이름과 일치하는 신청 내역이 없습니다.\n");
      return null;
    }

    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("아이디: %s\n", member.getId());
    return member;
  }

  @Override
  public void execute() {
  }
}
