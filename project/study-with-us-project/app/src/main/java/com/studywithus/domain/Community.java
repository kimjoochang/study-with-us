package com.studywithus.domain;

import java.sql.Date;

public class Community {

  public int no;
  public String title; // 게시글 제목
  public String writer; // 게시글 작성자
  public Date registeredDate; // 등록일
  public String content; // 내용
  public int like; // 좋아요
  public int viewCount; // 조회수
}