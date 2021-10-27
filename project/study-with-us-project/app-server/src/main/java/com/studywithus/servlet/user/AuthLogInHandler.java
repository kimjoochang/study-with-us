package com.studywithus.servlet.user;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.MemberDao;
import com.studywithus.domain.Member;
import com.studywithus.menu.Menu;

@WebServlet("/member/login")
public class AuthLogInHandler extends HttpServlet {
  private static final long serialVersionUID = 1L;
  SqlSession sqlSession;
  MemberDao memberDao;

  public static Member loginUser;
  public static int userAccessLevel = Menu.ACCESS_LOGOUT;

  public static Member getLoginUser() {
    return loginUser;
  }

  public static int getUserAccessLevel() {
    return userAccessLevel;
  }

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    memberDao = (MemberDao) servletContext.getAttribute("memberDao");
  }

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
    out.println("<h1>로그인 결과</h1>");

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    if (email.equals("root@test.com") && password.equals("0000")) {
      Member root = new Member();
      root.setName("관리자");
      root.setEmail("root@test.com");
      loginUser = root;
      userAccessLevel = Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL;
      out.println("관리자 계정으로 로그인하였습니다.\n");
      out.println("<br> <a href='/swu'>[메인]</a><br>");

    } else {
      try {
        Member member = memberDao.findMemberByEmailPassword(email, password);

        if (member != null) {
          out.printf("%s님 환영합니다!\n", member.getName());
          loginUser = member;
          userAccessLevel = Menu.ACCESS_GENERAL;
          out.println("<br> <a href='/swu'>[메인]</a><br>");
        }
      } catch (Exception e) {
        System.out.println("이메일과 암호가 일치하는 회원을 찾을 수 없습니다.");
        out.println("<br> <a href='/swu'>[메인]</a><br>");
        throw new ServletException();
      }
    }
  }
}