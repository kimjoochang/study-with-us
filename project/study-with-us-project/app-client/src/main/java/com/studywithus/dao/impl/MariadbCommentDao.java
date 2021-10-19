package com.studywithus.dao.impl;

import java.sql.Connection;
import java.util.HashMap;
import com.studywithus.dao.CommentDao;
import com.studywithus.domain.Comment;
import com.studywithus.request.RequestAgent;

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
    requestAgent.request("comment.insert", comment);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 데이터 저장 실패!");
    }
  }

  @Override
  public Comment findByNo(int no) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("comment.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Comment.class);
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String, String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("comment.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 삭제 실패!");
    }
  }

}
