package com.studywithus.dao;

import com.studywithus.domain.Comment;

public interface CommentDao {

  void insert(Comment comment) throws Exception;
  Comment findByNo(int no) throws Exception;
  void delete(int no) throws Exception;
}
