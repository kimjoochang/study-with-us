package com.studywithus.web.freestudy;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.StudyDao;
import com.studywithus.dao.StudyMemberDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@WebServlet("/freestudy/apply")
public class FreeStudyApplyController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao freeStudyDao;
  StudyMemberDao studyMemberDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    freeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
    studyMemberDao = (StudyMemberDao) servletContext.getAttribute("studyMemberDao");
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Member member = (Member) request.getSession().getAttribute("loginUser");

      /*
      int memberStatus = (int) studyMemberDao.findByNoStatus(member.getNo(), no).get("status");
      if (memberStatus == 0) {
        request.setAttribute("no", no);
        request.getRequestDispatcher("/chargestudy/detail").forward(request, response);

      } else if (memberStatus == 1) {
        request.setAttribute("no", no);
        request.getRequestDispatcher("/chargestudy/detail").forward(request, response);
      }

      // 모집인원 다 찼을 경우
      if (freeStudy.getMembers() == freeStudy.getMaxMembers()) {
        request.setAttribute("no", no);
        request.getRequestDispatcher("/chargestudy/detail").forward(request, response);
      }
       */

      studyMemberDao.insert(member.getNo(), no, Study.APPLICANT_STATUS);
      sqlSession.commit();

      response.sendRedirect("detail?no="+no);

    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
