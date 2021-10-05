package com.studywithus.table;

import com.studywithus.domain.Schedule;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.Request;
import com.studywithus.server.Response;

// 역할
// - 이달의 시험일정 데이터를 처리하는 역할을 한다.
// 
public class ExamScheduleTable extends JsonDataTable<Schedule> implements DataProcessor {

  public ExamScheduleTable() {
    super("examSchedule.json", Schedule.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "examSchedule.insert": insert(request, response); break;
      case "examSchedule.selectOne" : selectOne(request, response); break;
      case "examSchedule.selectList": selectList(request, response); break;
      case "examSchedule.update": update(request, response); break;
      case "examSchedule.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Schedule examSchedule = request.getObject(Schedule.class);

    if (!list.isEmpty()) {
      Schedule lastElement = list.get(list.size() - 1);
      examSchedule.setNo(lastElement.getNo() + 1);
    } else {
      examSchedule.setNo(1);
    }

    list.add(examSchedule);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Schedule m = findByNo(no);

    if (m != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(m);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    Schedule examSchedule = request.getObject(Schedule.class);

    int index = indexOf(examSchedule.getNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.set(index, examSchedule); 
    response.setStatus(Response.SUCCESS);
  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }
    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Schedule findByNo(int no) {
    for (Schedule s : list) {
      if (s.getNo() == no) {
        return s;
      }
    }
    return null;
  }

  private int indexOf(int scheduleNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == scheduleNo) {
        return i;
      }
    }
    return -1;
  }

}












