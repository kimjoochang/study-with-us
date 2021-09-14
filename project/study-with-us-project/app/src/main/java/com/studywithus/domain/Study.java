package com.studywithus.domain;

import java.sql.Date;
import java.util.List;

public class Study extends Content {
  private static final long serialVersionUID = 1L;

  private List<Member> members; // 팀원 or 멘티
  private List<Member> applicants; // 무료 스터디 신청자
  private String mentorExplanation; // 멘토 설명
  private String rule; // 스터디 규칙
  private int price; // 유료 스터디 가격
  private int onOffLine; // 온라인 or 오프라인
  private String area; // 지역
  private Date registeredDate; // 스터디 등록일
  private int like; // 좋아요
  private int viewCount; // 조회수

  public List<Member> getMembers() {
    return members;
  }

  public void setMembers(List<Member> members) {
    this.members = members;
  }

  public List<Member> getApplicants() {
    return applicants;
  }

  public void setApplicants(List<Member> applicants) {
    this.applicants = applicants;
  }

  public String getMentorExplanation() {
    return mentorExplanation;
  }

  public void setMentorExplanation(String mentorExplanation) {
    this.mentorExplanation = mentorExplanation;
  }

  public String getRule() {
    return rule;
  }

  public void setRule(String rule) {
    this.rule = rule;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getOnOffLine() {
    return onOffLine;
  }

  public void setOnOffLine(int onOffLine) {
    this.onOffLine = onOffLine;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public int getLike() {
    return like;
  }

  public void setLike(int like) {
    this.like = like;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }
}
