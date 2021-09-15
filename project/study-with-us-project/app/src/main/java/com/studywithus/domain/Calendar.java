package com.studywithus.domain;

public class Calendar extends Content{

	private static final long serialVersionUID = 1L;

	private String startDate; // 채용공고 시작일
	private String endDate; // 채용공고 종료일
	private String examDate; // 시험일

	@Override
	public String toCsvString() {
		return String.format("%s,%s,%s",
				this.getStartDate(),
				this.getEndDate(),
				this.getExamDate());
	}

	@Override
	public void loadCsv(String csv) {
		String[] values = csv.split(",");

		// CSV 문자열에서 추출한 값을 객체의 필드에 저장한다.
		this.setStartDate(values[0]);
		this.setEndDate(values[1]);
		this.setExamDate(values[2]);
	}

	public static Calendar valueOfCsv(String csv) {
		// 1) 한 줄의 문자열을 콤마(,)로 분리한다.
		String[] values = csv.split(",");

		// 2) 콤마로 분리한 값을 Calendar 객체에 담는다.
		Calendar c = new Calendar();
		c.setStartDate(values[0]);
		c.setEndDate(values[1]);
		c.setExamDate(values[2]);

		return c;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public String getExamDate() {
		return examDate;
	}

	public void setExamDate(String examDate) {
		this.examDate = examDate;
	}
}

