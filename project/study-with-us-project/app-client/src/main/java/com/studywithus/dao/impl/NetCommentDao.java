package com.studywithus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.dao.CommentDao;
import com.studywithus.domain.Comment;
import com.studywithus.request.RequestAgent;

// 역할
// - 커뮤니티 데이터를 서버를 통해 관리한다.
//
public class NetCommentDao implements CommentDao {

  RequestAgent requestAgent;

  public NetCommentDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Comment comment) throws Exception {
    requestAgent.request("comment.insert", comment);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 데이터 저장 실패!");
    }
  }

  @Override
  public List<Comment> findAll() throws Exception {
    requestAgent.request("comment.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 목록 조회 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Comment.class));
  }
  //
  //  @Override
  //  public List<Community> findByKeyword(String keyword) throws Exception {
  //    HashMap<String, String> params = new HashMap<>();
  //    params.put("keyword", keyword);
  //    requestAgent.request("community.selectListByKeyword", params);
  //
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      throw new Exception("게시글 검색 실패!");
  //    }
  //
  //    return new ArrayList<>(requestAgent.getObjects(Community.class));
  //  }
  //
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
  //
  //  @Override
  //  public void update(Community community) throws Exception {
  //    requestAgent.request("community.update", community);
  //
  //    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
  //      throw new Exception("게시글 변경 실패!");
  //    }
  //  }

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
