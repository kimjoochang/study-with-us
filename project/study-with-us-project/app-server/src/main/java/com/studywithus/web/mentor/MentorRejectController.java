package com.studywithus.web.mentor;

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
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.MentorApplication;

@WebServlet("/mentorapplication/reject")
public class MentorRejectController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  MentorApplicationDao mentorApplicationDao;
  MemberDao memberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    mentorApplicationDao = (MentorApplicationDao) servletContext.getAttribute("mentorApplicationDao");
    memberDao = (MemberDao) servletContext.getAttribute("memberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member member = (Member) request.getSession().getAttribute("loginUser");

    if (member.getUserAccessLevel() != 32) {
      response.sendRedirect("/swu/user/loginform");
    }

    int applicantNo = Integer.parseInt(request.getParameter("no"));

    try {
      MentorApplication mentorApplication = mentorApplicationDao.findByNo(applicantNo);
      mentorApplication.setRemarks(request.getParameter("remarks"));

      mentorApplicationDao.updateRejectStatus(mentorApplication);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      System.out.println(e.getMessage());
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
