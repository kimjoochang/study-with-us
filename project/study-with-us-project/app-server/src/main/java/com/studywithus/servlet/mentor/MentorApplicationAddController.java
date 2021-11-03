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
import com.studywithus.menu.Menu;

@WebServlet("/mentorapplication/add")
public class MentorApplicationAddController extends HttpServlet {
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

    if (member == null) {
      response.sendRedirect("/swu/user/loginform");
    }

    // 이미 멘토
    if ((member.getUserAccessLevel() & Menu.ACCESS_MENTOR) == 1) {
      response.sendRedirect("/swu");
    }

    try {
      MentorApplication mentorApplicantEmail = mentorApplicationDao.findByNo(member.getNo());

      // 신청서가 이미 있으면서 아직 승인/거절 결정이 안났다면 (visible이 true라면)
      if (mentorApplicantEmail != null && mentorApplicantEmail.getStatus() == 0) {
        response.sendRedirect("/swu");
      }

      MentorApplication mentorApplication = new MentorApplication();

      mentorApplication.setNo(member.getNo());
      mentorApplication.setSelfIntroduction(request.getParameter("selfIntro"));
      mentorApplication.setSubject(request.getParameter("subject"));

      mentorApplicationDao.insert(mentorApplication);
      sqlSession.commit();

      request.getRequestDispatcher("/swu/index").forward(request, response);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
