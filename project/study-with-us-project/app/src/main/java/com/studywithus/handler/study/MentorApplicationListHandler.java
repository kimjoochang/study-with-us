package com.studywithus.handler.study;

import java.util.List;
import com.studywithus.domain.MentorApplicationForm;
import com.studywithus.handler.Command;
import com.studywithus.handler.CommandRequest;

public class MentorApplicationListHandler implements Command {

  List<MentorApplicationForm> mentorApplicationFormList;

  public MentorApplicationListHandler (List<MentorApplicationForm> mentorApplicationFormList) {
    this.mentorApplicationFormList = mentorApplicationFormList;
  }

  @Override
  public void execute(CommandRequest request) {
    System.out.println("[멘토 승인 내역 / 조회]\n");

    if (mentorApplicationFormList.isEmpty() == true) {
      System.out.println("멘토 승인 내역이 존재하지 않습니다.");
      return;
    }

    for (MentorApplicationForm mentorApplication : mentorApplicationFormList) {
      System.out.printf("[멘토 신청자 = %s, 유료 스터디 주제 = %s, 신청일 = %s]\n",
          mentorApplication.getMentorMember().getName(),
          mentorApplication.getChargeStudySubject(),
          mentorApplication.getRegisteredDate());
    }
  }
}
