package com.studywithus.domain;

import java.io.Serializable;
import java.sql.Date;

@SuppressWarnings("serial")
public class Content implements Serializable {

  private int no;    // 게시글 번호
  private String title; // 게시글 제목
  private String content; // 게시글 내용
  private Member writer; // 게시글 작성자
  private Date registeredDate; // 등록일

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
  public String getContent() {
    return content;
  }
  public void setContent(String content) {
    this.content = content;
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

}
