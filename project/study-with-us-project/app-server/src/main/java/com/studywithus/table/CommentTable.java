package com.studywithus.table;

import com.studywithus.domain.Comment;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.Request;
import com.studywithus.server.Response;

// 역할
// - 댓글 데이터를 처리하는 일을 한다.
//
public class CommentTable extends JsonDataTable<Comment> implements DataProcessor {

  public CommentTable() {
    super("comment.json", Comment.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "comment.insert":
        insert(request, response);
        break;
      case "comment.selectList": selectList(request, response); break;
      //      case "community.selectListByKeyword":
      //        selectListByKeyword(request, response);
      //        break;
      case "comment.selectOne": selectOne(request, response); break;
      //      case "community.update":
      //        update(request, response);
      //        break;
      case "comment.delete":
        delete(request, response);
        break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Comment comment = request.getObject(Comment.class);

    if (list.isEmpty()) {
      comment.setNo(1);
    } else {
      Comment lastIndex = list.get(list.size() - 1);
      comment.setNo(lastIndex.getNo() + 1);
    }

    list.add(comment);
    response.setStatus(Response.SUCCESS);
  }
  //
  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }
  //
  //  private void selectListByKeyword(Request request, Response response) throws Exception {
  //    String keyword = request.getParameter("keyword");
  //
  //    ArrayList<Community> searchResult = new ArrayList<>();
  //    for (Community community : list) {
  //      if (!community.getTitle().contains(keyword) && !community.getContent().contains(keyword)
  //          && !community.getWriter().getName().contains(keyword)) {
  //        continue;
  //      }
  //      searchResult.add(community);
  //    }
  //
  //    response.setStatus(Response.SUCCESS);
  //    response.setValue(searchResult);
  //  }
  //
  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Comment comment = findByNo(no);

    if (comment != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(comment);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 커뮤니티를 찾을 수 없습니다.");
    }
  }
  //
  //  private void update(Request request, Response response) throws Exception {
  //    Community community = request.getObject(Community.class);
  //
  //    int index = indexOf(community.getNo());
  //    if (index == -1) {
  //      response.setStatus(Response.FAIL);
  //      response.setValue("해당 번호의 커뮤니티를 찾을 수 없습니다.");
  //      return;
  //    }
  //
  //    list.set(index, community);
  //    response.setStatus(Response.SUCCESS);
  //  }

  private void delete(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    int index = indexOf(no);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 댓글을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Comment findByNo(int no) {
    for (Comment comment : list) {
      if (comment.getNo() == no) {
        return comment;
      }
    }
    return null;
  }

  private int indexOf(int commentNo) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getNo() == commentNo) {
        return i;
      }
    }
    return -1;
  }
}
