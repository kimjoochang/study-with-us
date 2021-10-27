package com.studywithus.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


@WebServlet("/member/loginForm")
public class loginFormHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("  <title>로그인</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>로그인</h1>");

    out.println("<form action='login'>");
    out.println("이메일: <input type='text' name='email'><br>");
    out.println("비밀번호: <input type='text' name='password'><br>");
    out.println("<button>로그인</button><br>");
    out.println("</form>");

    out.println("</body>");
    out.println("</html>");
  }
}







