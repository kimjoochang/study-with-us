package com.studywithus.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Study extends Content {
  private List<Member> members = new ArrayList<>(); // 팀원 or 멘티
  private List<Member> applicants = new ArrayList<>(); // 무료 스터디 신청자
  private List<Member> likeMembers = new ArrayList<>(); // 관심목록 추가한 자
  private String mentorExplanation; // 멘토 설명
  private String rule; // 스터디 규칙
  private int price; // 유료 스터디 가격
  private int onOffLine; // 온라인 or 오프라인
  private String ONLINE = "온라인";
  private String OFFLINE = "오프라인";
  private String area; // 지역
  private Date registeredDate; // 스터디 등록일
  private int viewCount; // 조회수
  // [추가] 
  private int maxMembers; // 스터디 최대 모집인원 수
  private Date startDate; // 스터디 시작일
  private Date endDate; // 스터디 종료일

  private boolean deleteRequest; // 스터디 삭제요청 확인하기 위한 필드
  private String studyStatus;
  private List<Review> reviewList = new ArrayList<>();
  private List<String> likeMembersEmail = new ArrayList<>(); // 관심목록 추가한 회원의 이메일
  private List<String> menteeEmailList = new ArrayList<>(); // 스터디에 참여한 회원의 이메일

  public List<String> getMenteeEmailList() {
    return menteeEmailList;
  }

  public void setMenteeEmailList(List<String> menteeEmailList) {
    this.menteeEmailList = menteeEmailList;
  }

  public String getONLINE() {
    return ONLINE;
  }

  public String getOFFLINE() {
    return OFFLINE;
  }

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

  public List<Member> getLikeMembers() {
    return likeMembers;
  }

  public void setLikeMembers(List<Member> likeMembers) {
    this.likeMembers = likeMembers;
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

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public int getMaxMembers() {
    return maxMembers;
  }

  public void setMaxMembers(int maxMember) {
    this.maxMembers = maxMember;
  }

  public Date getStartDate() {
    return startDate;
  }

  public void setStartDate(Date startDate) {
    this.startDate = startDate;
  }

  public Date getEndDate() {
    return endDate;
  }

  public void setEndDate(Date endDate) {
    this.endDate = endDate;
  }

  public boolean isDeleteRequest() {
    return deleteRequest;
  }

  public void setDeleteRequest(boolean deleteRequest) {
    this.deleteRequest = deleteRequest;
  }

  public String getStudyStatus() {
    return studyStatus;
  }

  public void setStudyStatus(String studyStatus) {
    this.studyStatus = studyStatus;
  }

  public List<Review> getReviewList() {
    return reviewList;
  }

  public void setReviewList(List<Review> reviewList) {
    this.reviewList = reviewList;
  }

  public List<String> getLikeMembersEmail() {
    return likeMembersEmail;
  }

  public void setLikeMembersEmail(List<String> likeMembersEmail) {
    this.likeMembersEmail = likeMembersEmail;
  }
}
