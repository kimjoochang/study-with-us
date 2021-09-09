package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.Mentor;
import com.studywithus.util.Prompt;

public class MentorApplicantDetailHandler extends AbstractMentorApplicantHandler {

  public MentorApplicantDetailHandler(List<Member> mentorApplicantList, List<Mentor> mentorList) {
    super(mentorApplicantList, mentorList);
  }

  @Override
  public void execute() {
    System.out.println("[멘토 신청 내역 / 상세보기] \n");

    for(Member mentorApplicant : mentorApplicantList) {
      System.out.printf("%s, %s\n",mentorApplicant.getName(), mentorApplicant.getId());
    }

    String name = Prompt.inputString("이름? ");
    Member member = findByName(name);

    if (member == null) {
      System.out.println();
      System.out.println("입력하신 이름과 일치하는 신청 내역이 없습니다.\n");
      return;
    }

    System.out.println();
    System.out.printf("이름: %s\n", member.getName());
    System.out.printf("아이디: %s\n", member.getId());

    System.out.println("1. 승인");
    System.out.println("2. 거절");
    System.out.println("0. 이전");
    while(true) {
      int input = Prompt.inputInt("선택>");
      if(input == 1) {
        mentorApproveHandler(member);
      } else if (input == 2) {
        mentorRejectHandler(member);
      } else if (input == 0) {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
      return;
    }
  }
  private void mentorApproveHandler(Member mentorApplicant) {
    Mentor mentor = new Mentor();
    mentor.setId(mentorApplicant.getId());
    mentor.setName(mentorApplicant.getName());

    mentorList.add(mentor);
    mentorApplicantList.remove(mentorApplicant);

    System.out.println("멘토 승인이 완료되었습니다.");
  }
  private void mentorRejectHandler(Member mentorApplicant) {
    mentorApplicantList.remove(mentorApplicant);

    System.out.println("멘토 신청을 거절하였습니다.");
  }
}
