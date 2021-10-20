package com.studywithus.dao.impl;

import java.sql.Connection;
import java.util.List;
import com.studywithus.dao.ChargeStudyDao;
import com.studywithus.domain.Study;

public class MariadbChargeStudyDao implements ChargeStudyDao {

  Connection con;

  public MariadbChargeStudyDao(Connection con) {
    this.con = con;
  }

  @Override
  public void insert(Study chargeStudy) throws Exception {
  }

  @Override
  public List<Study> findAll() throws Exception {
    return null;
  }

  @Override
  public List<Study> findByKeyword(String keyword) throws Exception {
    return null;
  }

  @Override
  public Study findByNo(int no) throws Exception {
    return null;
  }

  @Override
  public void update(Study chargeStudy) throws Exception {

  }

  @Override
  public void delete(int no) throws Exception {

  }
}
