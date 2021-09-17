package com.studywithus.domain;

import java.sql.Date;
import java.util.List;

public class Community extends Content {

  private Date registeredDate; // 게시글 등록일
  private int viewCount; // 조회수
  private int like; // 좋아요
  private List<Member> members;//회원별 게시글 조회용 리스트

  public List<Member> getMembers() {
    return members;
  }

  public void setMembers(List<Member> members) {
    this.members = members;
  }

  @Override
  public String toCsvString() {
    return String.format("%s,%d,%d",
        this.getRegisteredDate(),
        this.getViewCount(),
        this.getLike());
  }

  @Override
  public void loadCsv(String csv) {
    String[] values = csv.split(",");

    // CSV 문자열에서 추출한 값을 객체의 필드에 저장한다.
    this.setRegisteredDate(Date.valueOf(values[0]));
    this.setViewCount(Integer.valueOf(values[1]));
    this.setLike(Integer.valueOf(values[2]));
  }

  public static Community valueOfCsv(String csv) {
    // 1) 한 줄의 문자열을 콤마(,)로 분리한다.
    String[] values = csv.split(",");

    // 2) 콤마로 분리한 값을 Community 객체에 담는다.
    Community cmnt = new Community();
    cmnt.setRegisteredDate(Date.valueOf(values[0]));
    cmnt.setViewCount(Integer.valueOf(values[1]));
    cmnt.setLike(Integer.valueOf(values[2]));

    return cmnt;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public int getViewCount() {
    return viewCount;
  }

  public void setViewCount(int viewCount) {
    this.viewCount = viewCount;
  }

  public int getLike() {
    return like;
  }

  public void setLike(int like) {
    this.like = like;
  }

}
