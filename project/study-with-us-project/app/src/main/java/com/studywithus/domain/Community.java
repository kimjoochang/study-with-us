package com.studywithus.domain;

import java.io.Serializable;
import java.sql.Date;

//커뮤니티 게시글 구성요소
@SuppressWarnings("serial")
public class Community implements Serializable {

  private int no; // 게시글 번호
  private String title; // 게시글 제목
  private String content; // 내용
  private Member writer; // 게시글 작성자 
  private Date registeredDate; // 등록일
  private int viewCount; // 조회수
  private int like; // 좋아요
  private String name;

  @Override
  public String toString() {
    return "Community [no=" + no + ", title=" + title + ", content=" + content + ", writer=" + writer
        + ", registeredDate=" + registeredDate + ", viewCount=" + viewCount + ", like=" + like
        + "]";
  }

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

  public Member getWriter() { 
    return writer;
  }

  public void setWriter(Member writer) { 
    this.writer = writer;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public String getContent() {
    return content;
  }

  public void setContent(String content) {
    this.content = content;
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

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

}
