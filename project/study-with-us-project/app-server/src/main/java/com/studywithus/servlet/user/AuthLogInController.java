package com.studywithus.servlet.user;

import java.io.IOException;
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

@WebServlet("/user/login")
public class AuthLogInController extends HttpServlet {
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

    String email = request.getParameter("email");
    String password = request.getParameter("password");

    //    if (email.equals("root@test.com") && password.equals("0000")) {
    //      Member root = new Member();
    //      root.setName("관리자");
    //      root.setEmail("root@test.com");
    //      loginUser = root;
    //      userAccessLevel = Menu.ACCESS_ADMIN | Menu.ACCESS_GENERAL;
    //      response.sendRedirect("http://localhost:8080/index");

    try {
      Member member = memberDao.findMemberByEmailPassword(email, password);

      if (member != null) {
        loginUser = member;
        userAccessLevel = Menu.ACCESS_GENERAL;
        response.sendRedirect("index.jsp");
      }
    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("Error.jsp").forward(request, response);
    }
  }
}
