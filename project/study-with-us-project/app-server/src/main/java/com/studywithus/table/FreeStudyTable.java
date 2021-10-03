package com.studywithus.table;

import java.util.ArrayList;
import com.studywithus.domain.Study;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.Request;
import com.studywithus.server.Response;

// 역할
// - 무료 스터디 데이터를 처리하는 일을 한다.
//
public class FreeStudyTable extends JsonDataTable<Study> implements DataProcessor {

  public FreeStudyTable() {
    super("freeStudy.json", Study.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "freeStudy.insert":
        insert(request, response);
        break;
      case "freeStudy.selectList":
        selectList(request, response);
        break;
      case "freeStudy.selectListByKeyword":
        selectListByKeyword(request, response);
        break;
      case "freeStudy.selectOne":
        selectOne(request, response);
        break;
      case "freeStudy.update":
        update(request, response);
        break;
      case "freeStudy.delete":
        delete(request, response);
        break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Study freeStudy = request.getObject(Study.class);
    list.add(freeStudy);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectListByKeyword(Request request, Response response) throws Exception {
    String keyword = request.getParameter("keyword");

    ArrayList<Study> searchResult = new ArrayList<>();
    for (Study freeStudy : list) {
      if (!freeStudy.getTitle().contains(keyword) && !freeStudy.getContent().contains(keyword)
          && !freeStudy.getWriter().getName().contains(keyword)) {
        continue;
      }
      searchResult.add(freeStudy);
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Study freeStudy = findByNo(no);

    if (freeStudy != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(freeStudy);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 무료 스터디를 찾을 수 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    Study freeStudy = request.getObject(Study.class);

    int index = indexOf(freeStudy.getNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 무료 스터디를 찾을 수 없습니다.");
      return;
    }

    list.set(index, freeStudy);
    response.setStatus(Response.SUCCESS);
  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 무료 스터디를 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Study findByNo(int no) {
    for (Study freeStudy : list) {
      if (freeStudy.getNo() == no) {
        return freeStudy;
      }
    }
    return null;
  }

  private int indexOf(int freeStudyNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == freeStudyNo) {
        return i;
      }
    }
    return -1;
  }
}


