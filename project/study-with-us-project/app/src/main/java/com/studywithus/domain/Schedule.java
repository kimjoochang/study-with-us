package com.studywithus.domain;

public class Schedule extends Content {
  private int yyyy; // 년
  private int mm; // 월
  private int dd; // 일
  private int startYyyy; // 시작일 년
  private int startMm; // 시작일 월
  private int startDd; // 시작일 일

  public int getYyyy() {
    return yyyy;
  }

  public void setYyyy(int yyyy) {
    this.yyyy = yyyy;
  }

  public int getMm() {
    return mm;
  }

  public void setMm(int mm) {
    this.mm = mm;
  }

  public int getDd() {
    return dd;
  }

  public void setDd(int dd) {
    this.dd = dd;
  }

  public int getStartYyyy() {
    return startYyyy;
  }

  public void setStartYyyy(int startYyyy) {
    this.startYyyy = startYyyy;
  }

  public int getStartMm() {
    return startMm;
  }

  public void setStartMm(int startMm) {
    this.startMm = startMm;
  }

  public int getStartDd() {
    return startDd;
  }

  public void setStartDd(int startDd) {
    this.startDd = startDd;
  }
}
