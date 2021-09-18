package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class MentorApplicationDetailHandler implements Command {

  //  MentorApplicationForm mentorApplication;

  List<MentorApplicationForm> mentorApplicationFormList;
  List<String> mentorList;
  List<Member> memberList;

  public MentorApplicationDetailHandler (List<MentorApplicationForm> mentorApplicationFormList, List<String> mentorList) {
    this.mentorApplicationFormList = mentorApplicationFormList;
    this.mentorList = mentorList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[멘토 신청 내역 / 상세보기]\n");

    // 멘토 신청자 List
    for (MentorApplicationForm mentorApplication : mentorApplicationFormList) {
      System.out.printf("[멘토 신청자 = %s, 유료 스터디 주제 = %s, 등록일 = %s]\n",
          mentorApplication.getName(),
          mentorApplication.getChargeStudySubject(),
          mentorApplication.getRegisteredDate());
    }

    System.out.println();

    String name = Prompt.inputString("이름: ");
    MentorApplicationForm mentorApplication = findByName(name);

    if (mentorApplication == null) {
      System.out.println();
      System.out.println("입력하신 이름과 일치하는 신청 내역이 없습니다.\n");
      return;
    }

    System.out.println();
    System.out.printf("신청자 이름: %s\n", mentorApplication.getName());
    System.out.printf("신청자 아이디: %s\n", mentorApplication.getId());
    System.out.printf("자기소개: %s\n", mentorApplication.getSelfIntroduction());
    System.out.printf("스터디 주제: %s\n", mentorApplication.getChargeStudySubject());
    System.out.printf("스터디 설명: %s\n", mentorApplication.getChargeStudyExplanation());
    System.out.printf("등록일: %s\n", mentorApplication.getRegisteredDate());

    System.out.println();
    System.out.println("1. 승인");
    System.out.println("2. 거절");
    System.out.println("0. 이전");

    while (true) {
      int input = Prompt.inputInt("선택> ");

      if (input == 1) {
        mentorApprove(mentorApplication);
        break;

      } else if (input == 2) {
        mentorReject(mentorApplication);
        break;

      } else if (input == 0) {
        return;

      } else {
        System.out.println("잘못된 번호입니다.");
        continue;
      }
    }
  }

  // [NullPoint 오류] 멘토 승인
  private void mentorApprove(MentorApplicationForm mentorApplication) {
    String id = mentorApplication.getId();
    findById(id).setUserAccessLevel(Menu.ACCESS_MENTOR);

    this.mentorList.add(mentorApplication.getId());
    this.mentorApplicationFormList.remove(mentorApplication);

    System.out.println("멘토 승인이 완료되었습니다.");
  }

  // [NullPoint 오류] 멘토 거절
  private void mentorReject(MentorApplicationForm mentorApplication) {
    this.mentorApplicationFormList.remove(mentorApplication);

    System.out.println("멘토 신청을 거절하였습니다.");
  }

  protected MentorApplicationForm findByName(String name) {
    for (MentorApplicationForm mentorApplication : mentorApplicationFormList) {
      if (mentorApplication.getName().equals(name)) {
        return mentorApplication;
      }
    }
    return null;
  }

  protected Member findById(String id) {
    for (Member member : memberList) {
      if (member.getId().equals(id)) {
        return member;
      }
    }
    return null;
  }
}
