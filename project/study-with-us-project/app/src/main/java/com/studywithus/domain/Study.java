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

  // 유/무료 스터디
  public static boolean value;

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

  // 스터디 등록일
  private Date registeredDate;

  // 좋아요
  private int like;

  // 조회수
  private int viewCount;

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

  //  public boolean isValue() {
  //    return value;
  //  }
  //
  //  public void setValue(boolean value) {
  //    this.value = value;
  //  }

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
