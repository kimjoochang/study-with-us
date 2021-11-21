package com.studywithus.servlet.user;

import org.apache.ibatis.session.SqlSession;

@Repository
public class UserRepositoryImpl implements UserRepository {
  @Autowired
  private SqlSession sqlSession;

  private static final String NAMESPACE = "com.studywithus.dao.MemberDao"; 

  // 아이디 중복 체크
  @Override
  public int emailCheck(String email) {
    int cnt = sqlSession.selectOne(NAMESPACE+".emailCheck", email);
    return cnt;
  }
}