package com.studywithus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplicationForm;

public class MariadbMentorApplicationDao implements MentorApplicationDao {

  Connection con;

  public MariadbMentorApplicationDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(MentorApplicationForm mentorApplication) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "insert into mentor(member_no,introduction,subject)" 
            + "values(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {
      stmt.setInt(1, mentorApplication.getNo());
      stmt.setString(2, mentorApplication.getSelfIntroduction());
      stmt.setString(3, mentorApplication.getChargeStudySubject());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("멘토 지원서 제출에 실패하였습니다.");
      }
    }
  }

  @Override
  public List<MentorApplicationForm> findAll() throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select"
            + " mt.member_no,"
            + " mt.introduction,"
            + " mt.subject,"
            + " mt.apply_date,"
            + " mt.status,"
            + " mt.remarks,"
            + " m.member_no,"
            + " m.name,"
            + " m.email"
            + " from"
            + " mentor mt"
            + " inner join member m on mt.member_no=m.member_no"
            + " order by member_no desc");
        ResultSet rs = stmt.executeQuery()) {

      ArrayList<MentorApplicationForm> list = new ArrayList<>();

      while(rs.next()) {
        MentorApplicationForm mentorApplicationForm = new MentorApplicationForm();
        mentorApplicationForm.setNo(rs.getInt("mt.member_no"));
        mentorApplicationForm.setSelfIntroduction(rs.getString("introduction"));
        mentorApplicationForm.setChargeStudySubject(rs.getString("subject"));
        mentorApplicationForm.setRegisteredDate(rs.getDate("apply_date"));
        mentorApplicationForm.setStatus(rs.getInt("status"));

        Member applicant = new Member();
        applicant.setNo(rs.getInt("m.member_no"));
        applicant.setName(rs.getString("name"));
        applicant.setEmail(rs.getString("email"));

        mentorApplicationForm.setMember(applicant);

        list.add(mentorApplicationForm);
      }
      return list;
    }
  }

  @Override
  public MentorApplicationForm findByNo(int no) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select"
            + " mt.member_no,"
            + " mt.introduction,"
            + " mt.subject,"
            + " mt.apply_date,"
            + " mt.status,"
            + " mt.remarks,"
            + " m.member_no,"
            + " m.name,"
            + " m.email"
            + " from"
            + " mentor mt"
            + " inner join member m on mt.member_no=m.member_no"
            + " where mt.member_no=" + no
            + " order by member_no desc");
        ResultSet rs = stmt.executeQuery()) {

      MentorApplicationForm mentorApplicationForm = null;

      if (mentorApplicationForm == null) {
        mentorApplicationForm = new MentorApplicationForm();

        mentorApplicationForm.setNo(rs.getInt("mt.member_no"));
        mentorApplicationForm.setSelfIntroduction(rs.getString("introduction"));
        mentorApplicationForm.setChargeStudySubject(rs.getString("subject"));
        mentorApplicationForm.setRegisteredDate(rs.getDate("apply_date"));
        mentorApplicationForm.setStatus(rs.getInt("status"));

        Member applicant = new Member();
        applicant.setNo(rs.getInt("m.member_no"));
        applicant.setName(rs.getString("name"));
        applicant.setEmail(rs.getString("email"));

        mentorApplicationForm.setMember(applicant);
      }
      return mentorApplicationForm;
    }
  }
  //
  @Override
  public void update(MentorApplicationForm mentorApplication) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "update mentor set"
            + " introduction=?,"
            + " subject=?,"
            + " status=?"
            + " remarks=?"
            + " member_no=?"
            + " where member_no=?")) {

      stmt.setString(1, mentorApplication.getSelfIntroduction());
      stmt.setString(2, mentorApplication.getChargeStudySubject());
      stmt.setInt(3, mentorApplication.getStatus());
      stmt.setString(4, mentorApplication.getRemarks());
      stmt.setInt(5, mentorApplication.getNo());
      stmt.setInt(6, mentorApplication.getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("멘토 승인(거절)을 실패했습니다.");
      }
    }

  }

}
