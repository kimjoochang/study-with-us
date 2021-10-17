package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.Comment;

public interface CommentDao {

  void insert(Comment comment) throws Exception;
  List<Comment> findAll() throws Exception;
  //  List<Study> findByKeyword(String keyword) throws Exception;
  Comment findByNo(int no) throws Exception;
  //  void update(Study chargeStudy) throws Exception;
  void delete(int no) throws Exception;
}