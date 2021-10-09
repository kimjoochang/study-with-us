package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.Community;

// 역할
// - 커뮤니티 데이터를 처리하는 객체 사용법을 정의한다.
//
public interface CommunityDao {
  void insert(Community community) throws Exception;

  List<Community> findAll() throws Exception;

  List<Community> findByKeyword(String keyword) throws Exception;

  Community findByNo(int no) throws Exception;

  void update(Community community) throws Exception;

  void delete(int no) throws Exception;
}
