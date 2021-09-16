package com.studywithus.domain;

public class Content {

  private int no; // 게시글 번호
  private Member writer; // 게시글 작성자
  private String title; // 게시글 제목
  private String content; // 게시글 내용

  public int getNo() {
    return no;
  }

  public void setNo(int no) {
    this.no = no;
  }

  public Member getWriter() {
    return writer;
  }

  public void setWriter(Member writer) {
    this.writer = writer;
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
}
