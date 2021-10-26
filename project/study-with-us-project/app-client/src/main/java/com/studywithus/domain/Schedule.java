package com.studywithus.domain;

import java.sql.Date;

public class Schedule extends Content {
	// [10.04 수정]
	private Date startDate; // 일정 시작일
	private Date endDate; // 일정 종료일

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
}
