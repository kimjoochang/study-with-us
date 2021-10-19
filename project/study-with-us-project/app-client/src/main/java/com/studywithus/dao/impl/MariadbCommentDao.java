package com.studywithus.dao.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import com.studywithus.dao.CommentDao;
import com.studywithus.domain.Comment;
import com.studywithus.domain.Member;

// 역할
// - 커뮤니티 데이터를 서버를 통해 관리한다.
//
public class MariadbCommentDao implements CommentDao {

  Connection con;

  public MariadbCommentDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Comment comment) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "insert into comment(content,member_no,cmnt_no)" 
            + "values(?,?,?)", Statement.RETURN_GENERATED_KEYS)) {

      stmt.setString(1, comment.getContent());
      stmt.setInt(2, comment.getWriter().getNo());
      stmt.setInt(3, comment.getCommunityNo());

      if (stmt.executeUpdate() == 0) {
        throw new Exception("댓글 등록에 실패하였습니다.");
      }
    }
  }

  @Override
  public Comment findByNo(int no) throws Exception {
    try(PreparedStatement stmt = con.prepareStatement(
        "select"
            + " c.comment_no,"
            + " c.content,"
            + " c.cmnt_no,"
            + " m.member_no,"
            + " m.name,"
            + " m.email"
            + " from"
            + " comment c"
            + " inner join member m on c.member_no=m.member_no"
            + " where c.comment_no=" + no
            + " order by member_no desc");
        ResultSet rs = stmt.executeQuery()) {

      Comment comment = null;

      while(rs.next()) {

        if (comment == null) {
          comment = new Comment();
          comment.setNo(rs.getInt("comment_no"));
          comment.setContent(rs.getString("content"));
          comment.setCommunityNo(rs.getInt("cmnt_no"));

          Member writer = new Member();
          writer.setNo(rs.getInt("member_no"));
          writer.setName(rs.getString("name"));
          writer.setEmail(rs.getString("email"));

          comment.setWriter(writer);
        }
      }
      return comment;
    }
  }

  @Override
  public void delete(int no) throws Exception {
    try (PreparedStatement stmt = con.prepareStatement(
        "delete from comment where comment_no=?")) {

      stmt.setInt(1, no);

      if (stmt.executeUpdate() == 0) {
        throw new Exception("커뮤니티 게시글 삭제에 실패했습니다.");
      }
    }

  }

}
