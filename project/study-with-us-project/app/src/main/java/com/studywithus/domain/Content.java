package com.studywithus.domain;

import java.io.Serializable;

@SuppressWarnings("serial")
public class Content implements Serializable {

  // 게시글 번호
  private int no;

  // 게시글 제목
  private String title;

  // 게시글 내용
  private String content;

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
}
