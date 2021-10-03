package com.studywithus.table;

import com.studywithus.domain.Payment;
import com.studywithus.domain.Study;
import com.studywithus.server.DataProcessor;
import com.studywithus.server.Request;
import com.studywithus.server.Response;

// 역할
// - 결제 기능에 관련된 데이터를 처리하는 역할을 한다.
// 
public class PaymentTable extends JsonDataTable<Payment> implements DataProcessor {

  public PaymentTable() {
    super("payment.json", Payment.class);
  }

  @Override
  public void execute(Request request, Response response) throws Exception {
    switch (request.getCommand()) {
      case "payment.insert": insert(request, response); break;
      //      case "member.selectList": selectList(request, response); break;
      case "payment.selectOne": selectOne(request, response); break;
      //      case "payment.selectOneByName": selectOneByName(request, response); break;
      //      case "member.update": update(request, response); break;
      //      case "member.delete": delete(request, response); break;
      default:
        response.setStatus(Response.FAIL);
        response.setValue("해당 명령을 지원하지 않습니다.");
    }
  }

  private void insert(Request request, Response response) throws Exception {

    Payment payment = request.getObject(Payment.class);
    list.add(payment);
    response.setStatus(Response.SUCCESS);
  }


  private void selectList(Request request, Response response) throws Exception {
    response.setStatus(Response.SUCCESS);
    response.setValue(list);
  }

  private void selectOne(Request request, Response response) throws Exception {
    int paidStudyNo = Integer.parseInt(request.getParameter("paidStudyNo"));
    Payment payment = findByPaidStudyNo(paidStudyNo);

    if (payment != null) {
      response.setStatus(Response.SUCCESS);
      response.setValue(payment);
    } else {
      response.setStatus(Response.FAIL);
      // response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
    }
  }

  private void update(Request request, Response response) throws Exception {
    Study chargeStudy = request.getObject(Study.class);

    int index = indexOf(chargeStudy.getNo());
    if (index == -1) {
      response.setStatus(Response.FAIL);
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
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
      response.setValue("해당 번호의 회원을 찾을 수 없습니다.");
      return;
    }

    list.remove(index);
    response.setStatus(Response.SUCCESS);
  }

  private Payment findByPaidStudyNo(int paidStudyNo) {
    for (Payment payment : list) {
      if (payment.getPaidStudyNo() == paidStudyNo) {
        return payment;
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












