package com.studywithus.dao.impl;

import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.CommentDao;
import com.studywithus.domain.Comment;

// 역할
// - 댓글 데이터를 DBMS 서버를 통해 관리한다.
//
public class MybatisCommentDao implements CommentDao {

  SqlSession sqlSession;

  public MybatisCommentDao(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Comment comment) throws Exception {
    sqlSession.insert("CommentMapper.insert", comment);
    sqlSession.commit();
  }

  @Override
  public List<Comment> findAll() throws Exception {
    return sqlSession.selectList("CommentMapper.findAll");
  }

  /*
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
   */

  @Override
  public void delete(int no) throws Exception {
    sqlSession.delete("CommentMapper.delete", no);
    sqlSession.commit();
  }
}
