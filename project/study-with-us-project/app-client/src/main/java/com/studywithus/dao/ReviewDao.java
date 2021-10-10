package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.Review;

public interface ReviewDao {
  void insert(Review review) throws Exception;
  List<Review> findAll() throws Exception;
}
