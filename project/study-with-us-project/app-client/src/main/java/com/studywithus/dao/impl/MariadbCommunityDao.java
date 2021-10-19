package com.studywithus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.util.List;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;

// 역할
// - 커뮤니티 데이터를 서버를 통해 관리한다.
//
public class MariadbCommunityDao implements CommunityDao {

  Connection con;

  public MariadbCommunityDao( Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Community community) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "insert into cmnt(category,title,content,member_no)" 
            + "values(?,?,?,?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

      stmt.setInt(1, community.getCategory());
      stmt.setString(2, community.getTitle());
      stmt.setString(3, community.getContent());
      stmt.setInt(4, community.getWriter().getNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("커뮤니티 글 등록에 실패하였습니다.");
      }
    }
  }

  @Override
  public List<Community> findAll() throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select"
            + " cm.cmnt_no,"
            + " cm.category,"
            + " cm.title,"
            + " cm.content,"
            + " cm.register_date,"
            + " cm.view_count,"
            + " c.comment_no,"
            + " c.content,"
            + " c.register_date,"
            + " from"
            + " cmnt cm"
            + " inner join member m on member_no=m.member_no"
        ))


    return null;
  }

  @Override
  public List<Community> findByKeyword(String keyword) throws Exception {
    return null;
  }

  @Override
  public Community findByNo(int no) throws Exception {
    return null;
  }

  @Override
  public void update(Community community) throws Exception {

  }

  @Override
  public void delete(int no) throws Exception {

  }

  //  @Override
  //  public List<Community> findAll() throws Exception {
  //    try (PreparedStatement stmt = con.prepareStatement(
  //        "select"
  //            + " cm.cmnt_no,"
  //            + " cm.category,"
  //            + " cm.title,"
  //            + " cm.content,"
  //            + " cm.register_date,"
  //            + " cm.view_count,"
  //            + " c.comment_no,"
  //            + " c.content,"
  //            + " c.register_date,"
  //            + " m.member_no cmnt_writer_no"
  //            + " m.member_email cmnt_writer_email"
  //            + " m2.member_email comment_writer_no"
  //            + " m2.member_email comment_writer_email"
  //            + " l.member_no like_member_no"
  //            + " l.member_email like_member_email"
  //            + " from" 
  //            + " cmnt cm"
  //            + " inner join member m on cm.member_no=m.member_no"
  //            + " left outer join comment c on cm.cmnt_no=c.cmnt_no"
  //            + " inner join member m2 on c.member_no=m2.member_no"
  //            + " left outer join like l on cm.cmnt_no=l.cmnt_no"
  //            + " inner join member m3 on c.member_no=m3.member_no"
  //            + " order by" 
  //            + " project_no desc, m2.name asc");
  //        ResultSet rs = stmt.executeQuery()) {
  //
  //      ArrayList<Community> list = new ArrayList<>();
  //
  //      int communitytNo = 0;
  //      Community community = null;
  //
  //      while (rs.next()) {
  //        if (communitytNo != rs.getInt("cmnt_no")) {
  //          community = new Community();
  //          community.setNo(rs.getInt("project_no"));
  //          community.setTitle(rs.getString("title"));
  //          community.setContent(rs.getString("content"));
  //          community.setViewCount(rs.getInt("view_count"));
  //          community.setRegisteredDate(rs.getDate("register_date"));
  //
  //          Member writer = new Member();
  //          writer.setNo(rs.getInt("owner_no"));
  //          writer.setEmail(rs.getString("owner_email"));
  //
  //          community.setWriter(writer);
  //
  //          list.add(community);
  //          communitytNo = community.getNo();
  //        }
  //
  //        if (rs.getString("comment_writer") != null) {
  //          Member member = new Member();
  //          member.setNo(rs.getInt("member_no"));
  //          member.setEmail(rs.getString("member_email"));
  //          comment.getMembers().add(member);
  //        }
  //      }
  //
  //      return list;
  //    }
  //  }
}