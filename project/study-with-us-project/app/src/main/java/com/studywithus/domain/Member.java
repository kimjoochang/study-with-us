package com.studywithus.domain;

import java.sql.Date;
import java.util.List;
import com.studywithus.csv.CsvValue;

public class Member implements CsvValue {

  private int userAccessLevel; // 권한
  private String name; // 회원 이름
  private String email; // 회원 이메일
  private String id; // 회원 아이디
  private String password; // 회원 비밀번호
  private String phoneNumber; // 회원 휴대폰 번호
  private Date registeredDate; // 회원 가입일
  private List<Study> freeInterest; // 무료 스터디 관심목록
  private List<Study> chargeInterest; // 유료 스터디 관심목록
  private List<Payment> payment; // 유료 스터디 결제목록

  @Override
  public String toCsvString() {

    StringBuilder strBuilder = new StringBuilder();

    strBuilder.append(String.format("%d,%s,%s,%s,%s,%d,%s,",
        this.getUserAccessLevel(),
        this.getName(),
        this.getEmail(),
        this.getId(),
        this.getPassword(),
        this.getPhoneNumber(),
        this.getRegisteredDate()));

    strBuilder.append(String.format("%d,", this.getFreeInterest().size()));

    for (Study freeInterest : this.getFreeInterest()) {
      strBuilder.append(String.format("%d,%s,%s,%s,%d,%d,", 
          freeInterest.getNo(), 
          freeInterest.getTitle(), 
          freeInterest.getWriter().getName(),
          freeInterest.getRegisteredDate(),
          freeInterest.getViewCount(), 
          freeInterest.getLike()));
    }

    strBuilder.append(String.format("%d,", this.getChargeInterest().size()));

    for (Study chargeInterest : this.getChargeInterest()) {
      strBuilder.append(String.format("%d,%s,%s,%s,%d,%d, ", 
          chargeInterest.getWriter().getName(), 
          chargeInterest.getRegisteredDate(), 
          chargeInterest.getViewCount(),
          chargeInterest.getLike()));
    }

    //    결제내역 리스트 완성 시 완성하기
    //    strBuilder.append(String.format("%d,", this.getPayment().size()));
    //
    //    for (Payment payment : this.getPayment()) {
    //      strBuilder.append(String.format("%d,%s,%s,%s,%d,%d,", 
    //
    //    }

    return strBuilder.toString();
  }

  @Override
  public void loadCsv(String csv) {
    String[] values = csv.split(",");

    this.setUserAccessLevel(Integer.valueOf(values[0]));
    this.setName(values[1]);
    this.setEmail(values[2]);
    this.setId(values[3]);
    this.setPassword(values[4]);
    this.setPhoneNumber(values[5]);
    this.setRegisteredDate(Date.valueOf(values[6]));

    int freeInterestSize = Integer.valueOf(values[7]);

    int lastIndex = 0;
    for (int i = 0, offset = 8; i < freeInterestSize; i++, offset += 7) {
      Study freeInterest = new Study();
      Member member = new Member();

      freeInterest.setNo(Integer.valueOf(values[offset])); 
      freeInterest.setTitle(values[offset+1]); 
      member.setName(values[offset+2]);
      freeInterest.setWriter(member);
      freeInterest.setRegisteredDate(Date.valueOf(values[offset+3]));
      freeInterest.setViewCount(Integer.valueOf(values[offset+4])); 
      freeInterest.setLike(Integer.valueOf(values[offset+5]));

      this.getFreeInterest().add(freeInterest);

      lastIndex = offset + 1;
    }

    int chargeInterestSize = Integer.valueOf(values[lastIndex + 1]);

    for (int i = 0, offset = lastIndex + 4; i < chargeInterestSize; i++, offset += 4) {

      Study chargeInterest = new Study();
      Member member = new Member();
      member.setName(values[offset]);
      chargeInterest.setWriter(member); 
      chargeInterest.setRegisteredDate(Date.valueOf(values[offset+1])); 
      chargeInterest.setViewCount(Integer.valueOf(values[offset+2]));
      chargeInterest.setLike(Integer.valueOf(values[offset+3]));

      this.getChargeInterest().add(chargeInterest);
    }

    //  결제내역 리스트 완성되면 완성하기

    //    int paymentSize = Integer.valueOf(values[lastIndex + 1]);
    //
    //    for (int i = 0, offset = lastIndex + ; i < paymentSize; i++, offset ) {
    //
    //      Payment payment = new Payment();
    //
    //      this.getPayment().add(payment);
    //    }

  }

  public int getUserAccessLevel() {
    return userAccessLevel;
  }

  public void setUserAccessLevel(int userAccessLevel) {
    this.userAccessLevel = userAccessLevel;
  }

  public String getName() {
    return name;
  }

  public void setName(String name) {
    this.name = name;
  }

  public String getEmail() {
    return email;
  }

  public void setEmail(String email) {
    this.email = email;
  }

  public String getId() {
    return id;
  }

  public void setId(String id) {
    this.id = id;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }

  public String getPhoneNumber() {
    return phoneNumber;
  }

  public void setPhoneNumber(String phoneNumber) {
    this.phoneNumber = phoneNumber;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
  }

  public List<Study> getFreeInterest() {
    return freeInterest;
  }

  public void setFreeInterest(List<Study> freeInterest) {
    this.freeInterest = freeInterest;
  }

  public List<Study> getChargeInterest() {
    return chargeInterest;
  }

  public void setChargeInterest(List<Study> chargeInterest) {
    this.chargeInterest = chargeInterest;
  }

  public List<Payment> getPayment() {
    return payment;
  }

  public void setPayment(List<Payment> payment) {
    this.payment = payment;
  }
}
