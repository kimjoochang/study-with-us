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
import com.studywithus.dao.MemberDao;
import com.studywithus.dao.MentorApplicationDao;
import com.studywithus.domain.Member;
import com.studywithus.menu.Menu;

@WebServlet("/mentorapplication/approve")
public class MentorApproveController extends HttpServlet {
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

    int no = Integer.parseInt(request.getParameter("no"));
    int applicantNo = Integer.parseInt(request.getParameter("applicantNo"));

    try {
      Member applicant = memberDao.findByNo(applicantNo);

      System.out.println(1);

      int temp = applicant.getUserAccessLevel();
      temp |= Menu.ACCESS_MENTOR;
      applicant.setUserAccessLevel(temp);

      memberDao.update(applicant);
      System.out.println(2);
      mentorApplicationDao.updateApproveStatus(no);
      System.out.println(3);

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
