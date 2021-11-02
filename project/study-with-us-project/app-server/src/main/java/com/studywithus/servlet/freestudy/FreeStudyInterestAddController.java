package com.studywithus.servlet.freestudy;

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
import com.studywithus.domain.Member;

@WebServlet("/freestudy/interest/add")
public class FreeStudyInterestAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao freeStudyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    freeStudyDao = (StudyDao) servletContext.getAttribute("freeStudyDao");
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Member member = (Member) request.getSession().getAttribute("loginUser");
    int freeStudyNo = Integer.parseInt(request.getParameter("no"));

    try {
      freeStudyDao.insertInterest(member.getNo(), freeStudyNo);
      sqlSession.commit();

      request.setAttribute("no", freeStudyNo);
      request.getRequestDispatcher("/freestudy/detail").forward(request, response);


    } catch (Exception e) {
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
