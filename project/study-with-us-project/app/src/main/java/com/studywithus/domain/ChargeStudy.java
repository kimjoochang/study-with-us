package com.studywithus.domain;

import java.sql.Date;

public class ChargeStudy {


	private String title; // 스터디 제목
	private int no; // 번호
	private String writer; // 멘토
	private String area; // 지역
	private String explanation; // 스터디 설명
	private Date registeredDate; // 등록일
	private int like; // 좋아요수
	private int viewCount; // 조회수
	private String mentorExplanation; // 멘토 설명
	private String review; // 후기
	private int price; // 가격
	//	public String payment; // 결제
	private String termsCondition; //약관동의

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

	public String getWriter() {
		return writer;
	}

	public void setWriter(String writer) {
		this.writer = writer;
	}

	public String getArea() {
		return area;
	}

	public void setArea(String area) {
		this.area = area;
	}

	public String getExplanation() {
		return explanation;
	}

	public void setExplanation(String explanation) {
		this.explanation = explanation;
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

	public String getMentorExplanation() {
		return mentorExplanation;
	}

	public void setMentorExplanation(String mentorExplanation) {
		this.mentorExplanation = mentorExplanation;
	}

	public String getReview() {
		return review;
	}

	public void setReview(String review) {
		this.review = review;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getTermsCondition() {
		return termsCondition;
	}

	public void setTermsCondition(String termsCondition) {
		this.termsCondition = termsCondition;
	}
}
