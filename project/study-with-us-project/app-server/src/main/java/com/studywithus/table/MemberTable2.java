package com.studywithus.table;

import com.studywithus.domain.Member;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.Request;
import com.studywithus.server.Response;

// 역할
// - 회원 데이터를 처리하는 일을 한다.
// 
public class MemberTable2 extends JsonDataTable<Member> implements DataProcessor {

  public MemberTable2() {
    super("member.json", Member.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "member.insert": insert(request, response); break;
      //      case "member.selectList": selectList(request, response); break;
      //      case "member.selectOne": selectOne(request, response); break;
      case "member.selectOneByEmailPassword": selectOneByEmailPassword(request, response); break;
      //      case "member.selectOneByName": selectOneByName(request, response); break;
      case "member.selectOneByEmail": selectOneByEmail(request, response); break;
      //      case "member.update": update(request, response); break;
      case "member.delete": delete(request, response); break;
      case "member.duplicateCheck": duplicateCheck(request, response); break;
      case "member.findMemberForResetPassword" : findMemberForResetPassword(request, response); break;
      case "member.resetPassword" : resetPassword(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {
    Member member = request.getObject(Member.class);
    list.add(member);
    response.setStatus(Response.SUCCESS);
  }

  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int no = Integer.parseInt(request.getParameter("no"));
    Member m = findByNo(no);

    if (m != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(m);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
    }
  }

  private void duplicateCheck(Request request, Response response) throws Exception {
    String email = request.getParameter("email");
    int type = 0;
    for (Member m : list) {
      if (email.equalsIgnoreCase(m.getEmail())) {
        type = 1;
        break;
      } else {
        continue;
      }
    }

    if (type == 0) {
      response.setStatus(Response.SUCCESS);
    } else {
      response.setStatus(Response.FAIL);
    }
  }

  private void findMemberForResetPassword(Request request, Response response) throws Exception {
    String name = request.getParameter("name");
    String email = request.getParameter("email");
    String phoneNumber = request.getParameter("phoneNumber");

    Member member = null;

    for (Member m : list) {
      if (m.getName().equals(name) && m.getEmail().equals(email)
          && m.getPhoneNumber().equals(phoneNumber)) {
        member = m;
        break;

      } else {
        continue;
      }
    }

    if (member != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(member);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이름과 이메일과 전화번호를 가진 회원을 찾을 수 없습니다.");
    }
  }

  private void resetPassword(Request request, Response response) throws Exception {
    Member member = request.getObject(Member.class);

    int index = indexOf(member.getEmail());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }

    list.set(index, member);
    response.setStatus(Response.SUCCESS);
  }


  private void selectOneByEmailPassword(Request request, Response response) throws Exception {
    String email = request.getParameter("email");
    String password = request.getParameter("password");

    Member member = null;
    for (Member m : list) {
      if (m.getEmail().equals(email) && m.getPassword().equals(password)) {
        member = m;
        break;
      }
    }

    if (member != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(member);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이메일과 암호를 가진 회원을 찾을 수 없습니다.");
    }
  }

  private void selectOneByName(Request request, Response response) throws Exception {
    String name = request.getParameter("name");
    System.out.println("-----> " + name);
    Member member = null;
    for (Member m : list) {
      if (m.getName().equals(name)) {
        member = m;
        break;
      }
    }

    if (member != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(member);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이름의 회원을 찾을 수 없습니다.");
    }
  }

  private void selectOneByEmail(Request request, Response response) throws Exception {
    String email = request.getObject(String.class);
    Member member = null;
    for (Member m : list) {
      if (m.getEmail().equals(email)) {
        member = m;
        break;
      }
    }

    if (member != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(member);
    } else {
      response.setStatus(Response.FAIL);
      response.setValue("해당 이메일의 회원을 찾을 수 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    Member member = request.getObject(Member.class);

    int index = indexOf(member.getNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }

    list.set(index, member);
    response.setStatus(Response.SUCCESS);
  }

  private void delete(Request request, Response response) throws Exception {
    String email = request.getObject(String.class);
    int index = indexOf(email);

    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Member findByNo(int no) {
    for (Member m : list) {
      if (m.getNo() == no) {
        return m;
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

  private int indexOf(String email) {
    for (int i = 0; i < list.size(); i++) {
      if (list.get(i).getEmail().equals(email)) {
        return i;
      }
    }
    return -1;
  }

}













