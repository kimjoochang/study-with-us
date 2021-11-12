package com.studywithus.servlet.user;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/mypage/myactivity")
public class MyChargeStudyActivityController extends HttpServlet {
  private static final long serialVersionUID = 1L;


  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    response.sendRedirect("../chargestudy/registerlist");
    //    request.getRequestDispatcher("../chargestudy/registerlist").forward(request, response);
  }
}