package com.studywithus.servlet.mentor;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplication;

@WebServlet("/mentorapplication/detail")
public class MentorApplicationDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MentorApplicationDao mentorApplicationDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    mentorApplicationDao = (MentorApplicationDao) servletContext.getAttribute("mentorApplicationDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member member = (Member) request.getSession().getAttribute("loginUser");

    if (member.getUserAccessLevel() != 32) {
      response.sendRedirect("/swu/user/loginform");
    }

    try {
      MentorApplication mentorApplication = mentorApplicationDao.findByNo(Integer.parseInt(request.getParameter("no")));

      request.setAttribute("mentorApplication", mentorApplication);

      request.getRequestDispatcher("/mentor/MentorApplicationDetail.jsp").forward(request, response);


    } catch (Exception e) {
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
