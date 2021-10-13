package com.studywithus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.dao.PaymentDao;
import com.studywithus.domain.Payment;
import com.studywithus.request.RequestAgent;

public class NetPaymentDao implements PaymentDao {

  RequestAgent requestAgent;

  public NetPaymentDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Payment payment) throws Exception {
    requestAgent.request("payment.insert", payment);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("결제내역 데이터 저장 실패!");
    }
  }

  @Override
  public List<Payment> findAll() throws Exception {
    requestAgent.request("payment.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("결제내역 목록 조회 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Payment.class));
  }

  @Override
  public Payment findByNo(int no, String email) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));
    params.put("email", String.valueOf(email));

    requestAgent.request("payment.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Payment.class);
  }

  @Override
  public void update(Payment payment) throws Exception {
    requestAgent.request("payment.update", payment);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("결제내역 변경 실패!");
    }
  }

}
