package com.studywithus.dao;

import java.util.List;
import com.studywithus.domain.DeleteRequestForm;

public interface DeleteRequestFormDao {

  List<DeleteRequestForm> findAllForAdmin() throws Exception;
  List<DeleteRequestForm> findAllForMember(int studyNo) throws Exception;
  DeleteRequestForm findByNo(int no) throws Exception;
  void insert(DeleteRequestForm deleteRequestForm) throws Exception;
  void updateRemarks(DeleteRequestForm deleteRequestForm) throws Exception;
  void delete(int no) throws Exception;
}

