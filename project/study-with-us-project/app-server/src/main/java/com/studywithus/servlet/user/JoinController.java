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

@WebServlet("/user/join")
public class JoinController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    memberDao = (MemberDao) servletContext.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {


    Member member = new Member();

    member.setName(request.getParameter("name"));
    member.setEmail(request.getParameter("email"));
    member.setNickname(request.getParameter("nickname"));
    member.setPassword(request.getParameter("password"));
    member.setPhoneNumber(request.getParameter("phoneNumber"));

    try {

      //      if (memberDao.findByEmail(member.getEmail()) != null) {
      //        response.sendRedirect("JoinForm.jsp");
      //      }

      memberDao.insert(member);
      sqlSession.commit();
      response.sendRedirect("/swu");


    } catch (Exception e) {
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("Error.jsp").forward(request, response);
    } 

  }

}
