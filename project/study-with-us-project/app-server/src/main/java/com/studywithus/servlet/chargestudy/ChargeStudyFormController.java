package com.studywithus.servlet.chargestudy;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/form")
public class ChargeStudyFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    // 출력을 담당할 뷰를 호출한다.
    request.getRequestDispatcher("/chargestudy/ChargeStudyForm.jsp").forward(request, response);
  }
}