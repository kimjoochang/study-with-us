package com.studywithus.table;

import java.util.ArrayList;
import com.studywithus.domain.Study;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.Request;
import com.studywithus.server.Response;

// 역할
// - 무료 스터디 데이터를 처리하는 일을 한다.
//
public class StudyTableGR extends JsonDataTable<Study> implements DataProcessor {

  String commandPrefix;

  public StudyTableGR(String filename, String commandPrefix) {
    super(filename, Study.class);
    this.commandPrefix = commandPrefix;
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    String action = request.getCommand().substring(commandPrefix.length());
    switch (action) {
      case "insert":
        insert(request, response);
        break;
      case "selectList":
        selectList(request, response);
        break;
      case "selectListByKeyword":
        selectListByKeyword(request, response);
        break;
      case "selectOne":
        selectOne(request, response);
        break;
      case "update":
        update(request, response);
        break;
      case "delete":
        delete(request, response);
        break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Study freeStudy = request.getObject(Study.class);

    if (list.isEmpty()) {
      freeStudy.setNo(1);
    } else {
      Study lastIndex = list.get(list.size() - 1);
      freeStudy.setNo(lastIndex.getNo() + 1);
    }

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

  // private void insertInterest(Request request, Response response) throws Exception {
  // Study freeStudyInterest = request.getObject(Study.class);
  //
  // freeStudyInterestList.add(freeStudyInterest);
  // response.setStatus(Response.SUCCESS);
  // }

  // private void selectListInterest(Request request, Response response) throws Exception {
  // response.setStatus(Response.SUCCESS);
  // response.setValue(freeStudyInterestList);
  // }

  // [삭제] 코드 중복
  // private void selectOneInterest(Request request, Response response) throws Exception {
  // int no = Integer.parseInt(request.getParameter("no"));
  // Study freeStudyInterest = findByNo(no);
  //
  // if (freeStudyInterest != null) {
  // response.setStatus(Response.SUCCESS);
  // response.setValue(freeStudyInterest);
  // } else {
  // response.setStatus(Response.FAIL);
  // response.setValue("해당 번호의 무료 스터디 관심 목록을 찾을 수 없습니다.");
  // }
  // }

  // private void deleteInterest(Request request, Response response) throws Exception {
  // int no = Integer.parseInt(request.getParameter("no"));
  // int index = indexOf(no);
  //
  // if (index == -1) {
  // response.setStatus(Response.FAIL);
  // response.setValue("해당 번호의 무료 스터디 관심 목록을 찾을 수 없습니다.");
  // return;
  // }
  //
  // freeStudyInterestList.remove(index);
  // response.setStatus(Response.SUCCESS);
  // }

  // [질문] 필요성
  // private void insertApply(Request request, Response response) throws Exception {
  // Study freeStudy = request.getObject(Study.class);
  //
  // if (list.isEmpty()) {
  // freeStudy.setNo(1);
  // } else {
  // Study lastIndex = list.get(list.size() - 1);
  // freeStudy.setNo(lastIndex.getNo() + 1);
  // }
  //
  // list.add(freeStudy);
  // response.setStatus(Response.SUCCESS);
  // }

  // [질문] 필요성
  // private void deleteApply(Request request, Response response) throws Exception {
  // int no = Integer.parseInt(request.getParameter("no"));
  // int index = indexOf(no);
  //
  // if (index == -1) {
  // response.setStatus(Response.FAIL);
  // response.setValue("해당 번호의 무료 스터디를 찾을 수 없습니다.");
  // return;
  // }
  //
  // list.remove(index);
  // response.setStatus(Response.SUCCESS);
  // }

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


