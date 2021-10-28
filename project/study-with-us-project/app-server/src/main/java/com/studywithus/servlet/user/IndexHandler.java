package com.studywithus.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.studywithus.domain.Member;


@WebServlet("/index")
public class IndexHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    response.setContentType("text/html;charset=UTF-8");
    PrintWriter out = response.getWriter();

    out.println("<html>");
    out.println("<head>");
    out.println("  <title>STUDY WITH US</title>");
    out.println("</head>");
    out.println("<body>");
    out.println("<h1>STUDY WITH US</h1>");

    Member member = AuthLogInHandler.getLoginUser();

    if (member == null) {
      out.println("<a href='signUp/form'>[회원가입]</a><br>");
      out.println("<a href='member/loginForm'>[로그인]</a><br>");
      out.println("</body>");
      out.println("</html>");

    } else {
      out.println("<br><h3>나의 정보</h1>");
      try {
        out.println(member.getName() + "님 환영합니다!");
        out.println("<br><a href='member/logout'>[로그아웃]</a><br>");
      } catch (Exception e) {
        throw new ServletException(e);
      }

      out.println("</body>");
      out.println("</html>");
    }
  }
}







