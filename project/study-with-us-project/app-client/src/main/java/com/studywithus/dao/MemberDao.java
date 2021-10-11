package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.Member;

public interface MemberDao {
  void insert(Member member) throws Exception;
  List<Member> findAll() throws Exception;
  Member findByNo(int no) throws Exception;
  Member findByName(String name) throws Exception;
  Member findByEmail(String email) throws Exception;
  void update(Member member) throws Exception;
  void delete(int no) throws Exception;
}
