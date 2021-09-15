package com.studywithus.domain;

import java.sql.Date;
import java.util.List;
import com.studywithus.csv.CsvValue;

public class MentorApplicationForm implements CsvValue {
  private String id; // 멘토 아이디
  private String name; // 멘토 신청 양식 작성자
  private String selfIntroduction; // 멘토 신청 회원 자기소개
  private String chargeStudySubject; //  유료 스터디 주제
  private String chargeStudyExplanation; // 유료 스터디 설명
  private Date registeredDate; // 등록일
  private List<Member> memberList;
  private List<String> mentorApplicationFormList;

  // 다음 메서드는 CsvValue 규칙에 따라 정의한 메서드다.
  @Override
  public String toCsvString() {
    return String.format("%s,%s,%s,%s,%s,%s",
        this.getId(),
        this.getName(),
        this.getSelfIntroduction(),
        this.getChargeStudySubject(),
        this.getChargeStudyExplanation(),
        this.getRegisteredDate());
  }

  // 다음 메서드는 파라미터로 받은 CSV 문자열에서 값을 추출하여 
  // MentorApplicationForm 객체의 각 필드에 저장한다.
  @Override
  public void loadCsv(String csv) {
    String[] values = csv.split(",");

    // CSV 문자열에서 추출한 값을 객체의 필드에 저장한다.
    this.setName(values[0]);
    this.setSelfIntroduction(values[1]);
    this.setChargeStudySubject(values[2]);
    this.setChargeStudyExplanation(values[3]);
    this.setRegisteredDate(Date.valueOf(values[4]));
  }

  public static MentorApplicationForm valueOfCsv(String csv) {
    // 1) 한 줄의 문자열을 콤마(,)로 분리한다.
    String[] values = csv.split(",");

    // 2) 콤마로 분리한 값을 MentorApplicationForm 객체에 담는다.
    MentorApplicationForm maf = new MentorApplicationForm();
    maf.setName(values[0]);
    maf.setSelfIntroduction(values[1]);
    maf.setChargeStudySubject(values[2]);
    maf.setChargeStudyExplanation(values[3]);
    maf.setRegisteredDate(Date.valueOf(values[4]));

    return maf;
  }

  protected Member findById(String id, List<String> mentorApplicationFormList) {
    for (Member member : memberList) {
      for (String mentorId : mentorApplicationFormList) {
        if (member.getId().equalsIgnoreCase(id)) {
          return mentorId;
        }
      }
      return null;
    }
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

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
}
