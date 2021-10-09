package com.studywithus.dao.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import com.studywithus.dao.ScheduleDao;
import com.studywithus.domain.Schedule;
import com.studywithus.request.RequestAgent;

public class NetScheduleDao implements ScheduleDao{

  RequestAgent requestAgent;
  String type;

  public NetScheduleDao(RequestAgent requestAgent, String type) {
    this.requestAgent = requestAgent;
    this.type = type;
  }

  @Override
  public void insert(Schedule schedule) throws Exception {
    requestAgent.request(type + ".insert", schedule);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("스케줄 데이터 저장을 실패하였습니다.");
    }
  }

  @Override
  public List<Schedule> findAll() throws Exception {
    requestAgent.request(type + ".selectList", null);
    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("스케줄 목록 조회를 실패하였습니다.");
    }

    return new ArrayList<>(requestAgent.getObjects(Schedule.class));
  }

  @Override
  public List<Schedule> findByKeyword(String keyword) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("keyword", keyword);
    requestAgent.request(type +  ".selectListByKeyword", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("스케줄 검색을 실패하였습니다.");
    }

    return new ArrayList<>(requestAgent.getObjects(Schedule.class));
  }

  @Override
  public Schedule findByNo(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request(type + ".selectOne", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      return null;
    }

    return requestAgent.getObject(Schedule.class);
  }

  @Override
  public void update(Schedule schedule) throws Exception {
    requestAgent.request(type + ".update", schedule);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("스케줄 변경을 실패하였습니다.");
    }
  }

  @Override
  public void delete(int no) throws Exception {
    HashMap<String,String> params = new HashMap<>();
    params.put("no", String.valueOf(no));

    requestAgent.request(type + ".delete", params);

    if (requestAgent.getStatus().equals(RequestAgent.FAIL)) {
      throw new Exception("스케줄 삭제를 실패하였습니다.");
    }
  }

  //  private String selectType(Schedule schedule) {
  //    if (schedule.getEndDate() == null) {
  //      return "examSchedule";
  //    } else {
  //      return "jobsSchedule";
  //    }
  //  }

}
