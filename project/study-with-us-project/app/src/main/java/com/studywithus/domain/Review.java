package com.studywithus.domain;

import java.sql.Date;

public class Review {

	private String name; // 유료 스터디 후기 작성자
	private String studyReview; // 유료 스터디 후기 // 멘토 후기?
	private String title; // 유료 스터디 제목
	private Date registeredDate; // 등록일 

	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getStudyReview() {
		return studyReview;
	}
	public void setStudyReview(String studyReview) {
		this.studyReview = studyReview;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Date getRegisteredDate() {
		return registeredDate;
	}
	public void setRegisteredDate(Date registeredDate) {
		this.registeredDate = registeredDate;
	}



}
