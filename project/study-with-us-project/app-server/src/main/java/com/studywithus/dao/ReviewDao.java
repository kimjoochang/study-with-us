package com.studywithus.dao;

import java.util.List;
import org.apache.ibatis.annotations.Param;
import com.studywithus.domain.Review;

public interface ReviewDao {
  List<Review> findAll(int studyNo) throws Exception;
  Review findByNo(@Param("memberNo") int memberNo, @Param("studyNo") int studyNo) throws Exception;
  void insert(Review review) throws Exception;
  void update(Review review) throws Exception;
  void delete(int no) throws Exception;
}
