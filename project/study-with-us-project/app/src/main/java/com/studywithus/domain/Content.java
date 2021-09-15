package com.studywithus.domain;

import com.studywithus.csv.CsvValue;

public class Content implements CsvValue {

  private int no; // 게시글 번호
  private Member writer; // 게시글 작성자
  private String title; // 게시글 제목
  private String content; // 게시글 내용


  @Override
  public String toCsvString() {
    return String.format("%d,%s,%s,%s,%d,%d,%d,%s",
        this.getNo(),
        this.getTitle(),
        this.getContent(),
        this.getWriter().getName());
  }

  @Override
  public void loadCsv(String csv) {
    String[] values = csv.split(",");

    this.setNo(Integer.valueOf(values[0]));
    this.setTitle(values[1]);
    this.setContent(values[2]);

    Member m = new Member();
    m.setName(values[3]);

    this.setWriter(m);
  }

  public static Content valueOfCsv(String csv) {
    // 1) 한 줄의 문자열을 콤마(,)로 분리한다.
    String[] values = csv.split(",");

    // 2) 콤마로 분리한 값을 Board 객체에 담는다.
    Content content = new Content();
    content.setNo(Integer.valueOf(values[0]));
    content.setTitle(values[1]);
    content.setContent(values[2]);

    // 3) 게시글을 작성한 회원 정보를 Member 객체에 담는다.
    Member m = new Member();
    m.setName(values[3]);

    // 4) Member 객체를 Board 객체의 작성자 필드에 저장한다.
    content.setWriter(m);

    return content;
  }


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
