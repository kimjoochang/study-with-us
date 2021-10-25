package com.studywithus.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

public class Study extends Content {
  private int no;
  private Member writer;
  @Override
  public Member getWriter() {
    return writer;
  }

  @Override
  public void setWriter(Member writer) {
    this.writer = writer;
  }

  private String area; // 지역
  private int onOffLine; // 오프라인 = 0 , 온라인 = 1 
  private Date registeredDate; // 스터디 등록일
  private int viewCount; // 조회수
  private int maxMembers; // 스터디 최대 모집인원 수
  private Date startDate; // 스터디 시작일
  private Date endDate; // 스터디 종료일
  private int studyStatus; // 모집중 = 0, 진행중 = 1, 진행완료 = 2

  // 기본값 = 0, 
  // 삭제요청(유료) = 1 
  // 삭제(무료)/삭제요청 승인(가격이 0 이상) = 2
  private int deleteStatus;
  private int price; // 유료 스터디 가격
  private List<Member> members = new ArrayList<>(); // 팀원 or 멘티
  private List<Member> applicants = new ArrayList<>(); // 팀원 or 멘티

  private List<Member> likeMembers = new ArrayList<>(); // 무료 스터디 신청자

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

  @Override
  public int getNo() {
    return no;
  }

  @Override
  public void setNo(int no) {
    this.no = no;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public int getOnOffLine() {
    return onOffLine;
  }

  public void setOnOffLine(int onOffLine) {
    this.onOffLine = onOffLine;
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

  public void setMaxMembers(int maxMembers) {
    this.maxMembers = maxMembers;
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

  public int getStudyStatus() {
    return studyStatus;
  }

  public void setStudyStatus(int studyStatus) {
    this.studyStatus = studyStatus;
  }

  public int getDeleteStatus() {
    return deleteStatus;
  }

  public void setDeleteStatus(int deleteStatus) {
    this.deleteStatus = deleteStatus;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public List<Member> getMembers() {
    return members;
  }

  public void setMembers(List<Member> members) {
    this.members = members;
  }

}
