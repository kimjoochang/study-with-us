package com.studywithus.dao.impl;

import java.util.ArrayList;
import java.util.List;
import com.studywithus.dao.ReviewDao;
import com.studywithus.domain.Review;
import com.studywithus.request.RequestAgent;

public class NetReviewDao implements ReviewDao {

  RequestAgent requestAgent;

  public NetReviewDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Review review) throws Exception {
    requestAgent.request("review.insert", review);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("후기 데이터 저장 실패!");
    }
  }

  @Override
  public List<Review> findAll() throws Exception {
    requestAgent.request("review.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("후기 조회 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Review.class));
  }
}
