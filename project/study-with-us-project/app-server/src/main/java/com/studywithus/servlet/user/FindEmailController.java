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

@WebServlet("/user/findemail")
public class FindEmailController extends HttpServlet {
  private static final long serialVersionUID = 1L;
  SqlSession sqlSession;
  MemberDao memberDao;

  // static Member loginUser;
  // public static Member getLoginUser() {
  //   return loginUser;
  // }

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    memberDao = (MemberDao) servletContext.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    String name = request.getParameter("name");
    String phoneNumber = request.getParameter("phoneNumber");
    //    Member loginUser = AuthLogInHandler.getLoginUser();

    try {
      Member member = memberDao.findMemberByNamePhoneNumber(name,phoneNumber);
      String email = request.getParameter("email");

      //      if (member != null) {
      //        HttpSession session = request.getSession();
      //        session.setAttribute("member", member);
      //      }

      response.sendRedirect("/swu/findemail");

    } catch (Exception e) {
      e.printStackTrace();
      request.setAttribute("error", e);
      request.getRequestDispatcher("Error.jsp").forward(request, response);
    }
  }
}
