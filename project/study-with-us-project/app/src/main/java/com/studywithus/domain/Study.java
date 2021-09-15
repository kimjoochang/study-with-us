package com.studywithus.domain;

import java.sql.Date;
import java.util.List;
import com.studywithus.csv.CsvValue;
import jdk.internal.org.jline.utils.ShutdownHooks.Task;

public class Study extends Content implements CsvValue {
  private List<Member> members; // 팀원 or 멘티
  private List<Member> applicants; // 무료 스터디 신청자
  private String mentorExplanation; // 멘토 설명
  private String rule; // 스터디 규칙
  private int price; // 유료 스터디 가격
  private int onOffLine; // 온라인 or 오프라인
  private String area; // 지역
  private Date registeredDate; // 스터디 등록일
  private int like; // 좋아요
  private int viewCount; // 조회수

  //다음 메서드는 CsvValue 규칙에 따라 정의한 메서드다.
  @Override
  public String toCsvString() {
    // 스터디 정보를 CSV로 출력할 때 스터디 정보를 포함한다.
    StringBuilder strBuilder = new StringBuilder();

    // 1) 스터디 기본 정보를 저장한다.
    strBuilder.append(String.format("%s,%s,%d,%d,%s,%s,%d,%d,",
        this.getMentorExplanation(),
        this.getRule(),
        this.getPrice(),
        this.getOnOffLine(),
        this.getArea(),
        this.getRegisteredDate(),
        this.getLike(),
        this.getViewCount(),
        getWriter().getName()));

    // 2) 스터디 참여자 정보를 저장한다.
    // => 스터디 참여자의 수를 저장한다.
    strBuilder.append(String.format("%d,", this.getMembers().size()));

    // => 스터디 참여자들의 정보를 저장한다.
    for (Member members : this.getMembers()) {
      strBuilder.append(String.format("%s,", members.getName()));
    }

    // 3) 스터디 신청자 정보를 저장한다.
    // => 신청자의 수를 저장한다.
    strBuilder.append(String.format("%d,", this.getApplicants().size()));

    // => 신청자들의 정보를 저장한다.
    for (Member applicants : this.getApplicants()) {
      strBuilder.append(String.format("%s,%s,%d,%d,%s,%s,%d,%d,", 
          applicants.getId()));
    }

    return strBuilder.toString();
  }

  // 다음 메서드는 파라미터로 받은 CSV 문자열에서 값을 추출하여 
  // Study 객체의 각 필드에 저장한다.
  @Override
  public void loadCsv(String csv) {
    String[] values = csv.split(",");

    // 1) 스터디 기본 정보를 로딩
    this.setMentorExplanation(values[0]);
    this.setRule(values[1]);
    this.setPrice(Integer.valueOf(values[2]));
    this.setOnOffLine(Integer.valueOf(values[3]));
    this.setArea(values[4]);
    this.setRegisteredDate(Date.valueOf(values[5]));
    this.setLike(Integer.valueOf(values[6]));
    this.setViewCount(Integer.valueOf(values[7]));

    // 2) 스터디 참여자 정보 로딩
    // => 스터디 참여자가 몇 명인지 읽어 온다.
    int memberSize = Integer.valueOf(values[8]);

    int lastIndex = 0;
    for (int i = 0, offset = 9; i < memberSize; i++, offset += 2) {
      // => 파일에서 참여자 이름을 로딩한다.
      Member member = new Member();
      member.setName(values[offset]);

      // => 스터디에 참여자를 추가한다.
      this.getMembers().add(member);

      // => 신청자 데이터를 읽을 때 사용할 마지막 인덱스 번호를 저장해 둔다.
      lastIndex = offset + 1;
    }

    // 4) 스터디 신청자 정보 로딩
    // => 스터디 신청자가 몇 명인지 읽어 온다.
    int taskSize = Integer.valueOf(values[lastIndex + 1]);

    for (int i = 0, offset = lastIndex + 2; i < taskSize; i++, offset += 6) {
      // => 파일에서 작업 데이터를 로딩한다.
      Task t = new Task();
      t.setNo(Integer.valueOf(values[offset]));
      t.setContent(values[offset + 1]);
      t.setDeadline(Date.valueOf(values[offset + 2]));
      t.setStatus(Integer.valueOf(values[offset + 3]));

      // => 작업자 데이터 로딩
      Member worker = new Member();
      worker.setNo(Integer.valueOf(values[offset + 4]));
      worker.setName(values[offset + 5]);

      // => 작업에 작업자 정보를 등록한다.
      t.setOwner(worker);

      // => 스터디에 작업을 추가한다.
      this.getApplicants().add(t);
    }
  }

  public List<Member> getMembers() {
    return members;
  }

  public void setMembers(List<Member> members) {
    this.members = members;
  }

  public List<Member> getApplicants() {
    return applicants;
  }

  public void setApplicants(List<Member> applicants) {
    this.applicants = applicants;
  }

  public String getMentorExplanation() {
    return mentorExplanation;
  }

  public void setMentorExplanation(String mentorExplanation) {
    this.mentorExplanation = mentorExplanation;
  }

  public String getRule() {
    return rule;
  }

  public void setRule(String rule) {
    this.rule = rule;
  }

  public int getPrice() {
    return price;
  }

  public void setPrice(int price) {
    this.price = price;
  }

  public int getOnOffLine() {
    return onOffLine;
  }

  public void setOnOffLine(int onOffLine) {
    this.onOffLine = onOffLine;
  }

  public String getArea() {
    return area;
  }

  public void setArea(String area) {
    this.area = area;
  }

  public Date getRegisteredDate() {
    return registeredDate;
  }

  public void setRegisteredDate(Date registeredDate) {
    this.registeredDate = registeredDate;
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
}
