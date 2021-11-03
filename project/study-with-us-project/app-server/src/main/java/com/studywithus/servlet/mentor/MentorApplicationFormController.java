package com.studywithus.servlet.mentor;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet("/mentorapplication/form")
public class MentorApplicationFormController extends HttpServlet{

  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    HttpSession session = request.getSession(false);

    if (session.getAttribute("loginUser") == null) {
      response.sendRedirect("/swu/user/loginform");

    } else {
      request.getRequestDispatcher("/mentor/MentorApplicationForm.jsp").forward(request, response);
    }
  }
}
