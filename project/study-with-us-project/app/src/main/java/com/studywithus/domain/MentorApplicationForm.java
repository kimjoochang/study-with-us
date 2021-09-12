package com.studywithus.domain;

import java.sql.Date;

public class MentorApplicationForm {

  // 멘토 신청 양식 작성자
  private String name;

  // 멘토 신청 회원 자기소개
  private String selfIntroduction;

  //  유료 스터디 주제
  private String chargeStudySubject;

  // 유료 스터디 설명
  private String chargeStudyExplanation;

  // 등록일
  private Date registeredDate;

  // 멘토 신청자 정보
  private Member mentorApplicantInfo;

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
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

  public Member getMentorApplicantInfo() {
    return mentorApplicantInfo;
  }

  public void setMentorApplicantInfo(Member mentorApplicantInfo) {
    this.mentorApplicantInfo = mentorApplicantInfo;
  }
}
