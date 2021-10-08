package com.studywithus.table;

import java.util.ArrayList;
import com.studywithus.domain.Study;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.Request;
import com.studywithus.server.Response;

// 역할
// - 회원 데이터를 처리하는 일을 한다.
// 
public class ChargeStudyTableJJ extends JsonDataTable<Study> implements DataProcessor {

  public ChargeStudyTableJJ() {
    super("chargeStudy.json", Study.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "chargeStudy.insert": insert(request, response); break;
      case "chargeStudy.selectList": selectList(request, response); break;
      case "chargeStudy.selectOne": selectOne(request, response); break;
      case "chargeStudy.selectListByKeyword" : selectListByKeyword(request, response); break;
      case "chargeStudy.selectOneByNo": selectOneByNo(request, response); break;
      //      case "member.selectOneByName": selectOneByName(request, response); break;
      case "chargeStudy.update": update(request, response); break;
      case "chargeStudy.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Study chargeStudy = request.getObject(Study.class);

    if (!list.isEmpty()) {
      Study lastElement = list.get(list.size() - 1);
      chargeStudy.setNo(lastElement.getNo() + 1);
    } else {
      chargeStudy.setNo(1);
    }

    list.add(chargeStudy);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectListByKeyword(Request request, Response response) throws Exception {
    String keyword = request.getParameter("keyword");

    ArrayList<Study> searchResult = new ArrayList<>();
    for (Study chargeStudy : list) {
      if (!chargeStudy.getTitle().contains(keyword) &&
          !chargeStudy.getContent().contains(keyword) &&
          !chargeStudy.getWriter().getName().contains(keyword)) {
        continue;
      }
      searchResult.add(chargeStudy);
    }

    response.setStatus(Response.SUCCESS);
    response.setValue(searchResult);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Study chargeStudy = findByNo(no);

    if (chargeStudy != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(chargeStudy);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    Study chargeStudy = request.getObject(Study.class);

    int index = indexOf(chargeStudy.getNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 게시글을 찾을 수 없습니다.");
      return;
    }

    list.set(index, chargeStudy);
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

  private void selectOneByNo(Request request, Response response) {
    Study chargeStudy = null;
    int no = request.getObject(Integer.class);
    for (Study study : list) {
      if (study.getNo() == no) {
        chargeStudy = study;
        break;

      } else {
        continue;
      }
    }
    if (chargeStudy != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(chargeStudy);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 스터디를 찾을 수 없습니다.");
    }
  }

  private Study findByNo(int no) {
    for (Study chargeStudy : list) {
      if (chargeStudy.getNo() == no) {
        return chargeStudy;
      }
    }
    return null;
  }

  private int indexOf(int memberNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == memberNo) {
        return i;
      }
    }
    return -1;
  }

}












