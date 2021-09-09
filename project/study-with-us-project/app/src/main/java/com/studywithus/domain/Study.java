package com.studywithus.domain;

import java.io.Serializable;
import java.sql.Date;
import java.util.List;

@SuppressWarnings("serial")
public class Study implements Serializable {
  // 팀장 or 멘토
  private Member writer;

  // 팀원 or 멘티
  private List<Member> members;

  // 무료 스터디
  private List<Study> freeStudy;

  // 유료 스터디
  private List<Study> chargeStudy;

  // 게시글 번호
  private int no;

  // 제목
  private String title;

  // 스터디 설명
  private String explanation;

  // 멘토 설명
  private String mentorExplanation;

  // 스터디 규칙
  private String rule;

  // 유료 스터디 가격
  private int price;

  // 온라인 or 오프라인
  private int onOffLine;

  // 지역
  private String area;

  // 유료 스터디 후기
  private String review;

  // 스터디 등록일
  private Date registeredDate;

  // 좋아요
  private int like;

  // 조회수
  private int viewCount;

  // 무료 스터디 관심목록
  private List<Study> freeInterest;

  // 유료 스터디 관심목록
  private List<Study> chargeInterest;

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
  }

  public List<Member> getMembers() {
    return members;
  }

  public void setMembers(List<Member> members) {
    this.members = members;
  }

  public List<Study> getFreeStudy() {
    return freeStudy;
  }

  public void setFreeStudy(List<Study> freeStudy) {
    this.freeStudy = freeStudy;
  }

  public List<Study> getChargeStudy() {
    return chargeStudy;
  }

  public void setChargeStudy(List<Study> chargeStudy) {
    this.chargeStudy = chargeStudy;
  }

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public String getTitle() {
    return title;
  }

  public void setTitle(String title) {
    this.title = title;
  }

  public String getExplanation() {
    return explanation;
  }

  public void setExplanation(String explanation) {
    this.explanation = explanation;
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

  public String getReview() {
    return review;
  }

  public void setReview(String review) {
    this.review = review;
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

  public List<Study> getFreeInterest() {
    return freeInterest;
  }

  public void setFreeInterest(List<Study> freeInterest) {
    this.freeInterest = freeInterest;
  }

  public List<Study> getChargeInterest() {
    return chargeInterest;
  }

  public void setChargeInterest(List<Study> chargeInterest) {
    this.chargeInterest = chargeInterest;
  }
}
