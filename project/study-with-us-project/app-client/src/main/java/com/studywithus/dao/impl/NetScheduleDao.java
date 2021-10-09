package com.studywithus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.dao.ScheduleDao;
import com.studywithus.domain.Schedule;
import com.studywithus.request.RequestAgent;

public class NetScheduleDao implements ScheduleDao{

  RequestAgent requestAgent;

  public NetScheduleDao(RequestAgent requestAgent) {
    this.requestAgent = requestAgent;
  }

  @Override
  public void insert(Schedule schedule) throws Exception {
    requestAgent.request("board.insert", schedule);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글   데이터 저장 실패!");
    }
  }

  @Override
  public List<Board> findAll() throws Exception {
    requestAgent.request("board.selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 목록 조회 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Board.class));
  }

  @Override
  public List<Board> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request("board.selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 검색 실패!");
    }

    return new ArrayList<>(requestAgent.getObjects(Board.class));
  }

  @Override
  public Board findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Board.class);
  }

  @Override
  public void update(Board board) throws Exception {
    requestAgent.request("board.update", board);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 변경 실패!");
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request("board.delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("게시글 삭제 실패!");
    }
  }

}
