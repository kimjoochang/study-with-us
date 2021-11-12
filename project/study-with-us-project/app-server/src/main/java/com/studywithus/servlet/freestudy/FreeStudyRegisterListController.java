package com.studywithus.servlet.freestudy;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.studywithus.dao.StudyDao;
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@WebServlet("/freestudy/registerlist")
public class FreeStudyRegisterListController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao freeStudyDao;
  StudyMemberDao studyMemberDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    freeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
    studyMemberDao = (StudyMemberDao) servletContext.getAttribute("studyMemberDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {
    try {
      Member member = (Member) request.getSession().getAttribute("loginUser");

      if (member == null) {
        response.sendRedirect("/swu/user/loginform");
      }

      Collection<Study> freeStudyList = 
          studyMemberDao.findAllStudy(member.getNo(),Study.OWNER_STATUS,0,0);

      request.setAttribute("studyList", freeStudyList);

      request.getRequestDispatcher("../MyPage_freeStudy.jsp").forward(request, response);   

      return;
    } catch (Exception e) {
      System.out.println(e.getMessage());
      //      request.setAttribute("error", e);
      //      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}