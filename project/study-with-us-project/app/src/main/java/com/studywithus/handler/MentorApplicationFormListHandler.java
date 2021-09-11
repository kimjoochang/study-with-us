package com.studywithus.handler;

import java.util.List;
import com.studywithus.domain.Member;
import com.studywithus.menu.Menu;
import com.studywithus.util.Prompt;

public class MentorApplicationFormListHandler implements Command {

  List<Member> mentorApplicationFormList;

  static Member loginUser;
  static int userAccessLevel = Menu.ACCESS_GENERAL;

  public static Member getLoginUser() {
    return loginUser;
  }

  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  // 멘토 신청 회원 목록 보기
  @Override
  public void execute() {
    System.out.println("[멘토 신청 내역 / 보기] \n");

    for(Member mentorApplicationForm : mentorApplicationFormList) {
      System.out.println("신청자 이름 : " + mentorApplicationForm.getName() + "/" + "멘토 신청서 ");
    }

    // 멘토 신청 내역 상세 보기
    System.out.println("[멘토 신청 내역 / 상세보기] \n");

    for(Member mentorApplicant : mentorApplicationFormList) {
      System.out.printf("%s, %s\n",mentorApplicant.getName(), mentorApplicant.getId());
    }

    String name = Prompt.inputString("이름? ");
    Member member = findByName(name);
    String selfIntroduction =  Prompt.inputString("멘토 소개? ");

    if (member == null) {
      System.out.println();
      System.out.println("입력하신 이름과 일치하는 멘토 신청 내역이 없습니다.\n");
      return;
    }

    System.out.println();
    System.out.printf("멘토 이름: %s\n", member.getName());
    //		System.out.printf("멘토 신청 회원 자기소개: %s\n", member.selfIntroduction());
    //		System.out.printf("유료 스터디 주제: %s\n", member.chargeStudySubject());
    //		System.out.printf("유료 스터디 설명: %s\n", member.chargeStudyExplanation());
    //		System.out.printf("등록일: %s\n", member.registeredDate());

  }

  private Member findByName(String name) {
    // TODO Auto-generated method stub
    return null;
  }

}

