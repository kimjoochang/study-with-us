package com.studywithus.domain;

import java.sql.Date;

public class Board {

	public int no;
	public String title; // 무료 유료 - 스터디 제목
	public String writer; // 무료-팀장, 유료-멘토
	public String onOffLine; // 무료-온오프라인
	public String area; // 무료 유료 - 지역
	public String explanation; // 무료 유료 - 스터디 설명
	public String rule; // 무료 - 스터디 룰
	public Date registeredDate; // 무료 유료 - 등록일
	public int like; // 무료 유료 - 인기순(좋아요수)
	public int viewCount; // 무료 유료 - 조회
	public String mentorExplanation; // 유료 - 멘토 설명
	public String review; // 유료 - 후기
	public int price; // 유료 - 가격
	public String payment; // 유료 - 결제
	public String termsCondition; //유료 약관동

}

