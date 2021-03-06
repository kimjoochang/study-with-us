package com.studywithus.dao.impl;

import java.util.HashMap;
import java.util.List;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;

// 역할
// - 회원 데이터를 DBMS 서버를 통해 관리한다.
//
public class MybatisMemberDaoJJ implements MemberDao {

  SqlSession sqlSession;

  public MybatisMemberDaoJJ(SqlSession sqlSession) {
    this.sqlSession = sqlSession;
  }

  @Override
  public void insert(Member member) throws Exception {
    sqlSession.insert("MemberMapperJJ.insert", member);
    sqlSession.commit();
  }

  @Override
  public List<Member> findAll() throws Exception {
    return sqlSession.selectList("MemberMapperJJ.findAll");
  }

  @Override
  public Member findByNo(int no) throws Exception {
    return sqlSession.selectOne("MemberMapperJJ.findByNo", no);
  }

  @Override
  public Member findByName(String name) throws Exception {
    List<Member> list = sqlSession.selectList("MemberMapperJJ.findByName", name);
    if (list.size() > 0) {
      return list.get(0);
    } else {
      return null;
    }
  }

  @Override
  public Member findByEmail(String email) throws Exception {
    return sqlSession.selectOne("MemberMapperJJ.findByName", email);
  }

  @Override
  public Member findMemberByNamePhoneNumber(String name, String phoneNumber) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("name", name);
    params.put("phoneNumber", phoneNumber);

    return sqlSession.selectOne("MemberMapperJJ.findMemberByNamePhoneNumber", params);
  }

  @Override
  public Member findMember(String name, String email, String phoneNumber) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("name", name);
    params.put("email", email);
    params.put("phoneNumber", phoneNumber);

    return sqlSession.selectOne("MemberMapperJJ.findMember", params);
  }

  @Override
  public Member findMemberByEmailPassword(String email, String password) throws Exception {
    HashMap<String,Object> params = new HashMap<>();
    params.put("email", email);
    params.put("password", password);

    return sqlSession.selectOne("MemberMapperJJ.findMemberByEmailPassword", params);
  }

  @Override
  public void update(Member member) throws Exception {
    sqlSession.update("MemberMapperJJ.update", member);
    sqlSession.commit();
  }

  @Override
  public void delete(int no) throws Exception {
    sqlSession.delete("MemberMapperJJ.delete", no);
    sqlSession.commit();
  }

}
