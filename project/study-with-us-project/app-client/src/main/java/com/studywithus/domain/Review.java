package com.studywithus.domain;

import java.util.List;

public class Review extends Content {
  private List<Review> chargeStudyReviewList; // 각각의 유료 스터디별 리뷰
  private List<Review> myReviewList; // 내가 쓴 유료 스터디 리뷰 (회원 관점)
  private List<Review> mentorReviewList; // 내가 생성한 스터디 리뷰 (멘토 관점)

  public List<Review> getChargeStudyReviewList() {
    return chargeStudyReviewList;
  }

  public void setChargeStudyReviewList(List<Review> chargeStudyReviewList) {
    this.chargeStudyReviewList = chargeStudyReviewList;
  }

  public List<Review> getMyReviewList() {
    return myReviewList;
  }

  public void setMyReviewList(List<Review> myReviewList) {
    this.myReviewList = myReviewList;
  }

  public List<Review> getMentorReviewList() {
    return mentorReviewList;
  }

  public void setMentorReviewList(List<Review> mentorReviewList) {
    this.mentorReviewList = mentorReviewList;
  }
}
