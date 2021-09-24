package com.studywithus.domain;

import java.sql.Date;

public class MentorApplicationForm {
  private Member mentorMember; // 멘토 신청자
  private String selfIntroduction; // 멘토 신청 회원 자기소개
  private String chargeStudySubject; //  유료 스터디 주제
  private String chargeStudyExplanation; // 유료 스터디 설명
  private Date registeredDate; // 신청일

  public Member getMentorMember() {
    return mentorMember;
  }

  public void setMentorMember(Member memtorMember) {
    this.mentorMember = memtorMember;
  }

  public String getSelfIntroduction() {
    return selfIntroduction;
  }

  public void setSelfIntroduction(String selfIntroduction) {
    this.selfIntroduction = selfIntroduction;
  }

  public String getChargeStudySubject() {
    return chargeStudySubject;
  }

  public void setChargeStudySubject(String chargeStudySubject) {
    this.chargeStudySubject = chargeStudySubject;
  }

  public String getChargeStudyExplanation() {
    return chargeStudyExplanation;
  }

  public void setChargeStudyExplanation(String chargeStudyExplanation) {
    this.chargeStudyExplanation = chargeStudyExplanation;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }
}
