package com.studywithus.servlet.chargestudy;

import java.io.IOException;
import java.sql.Date;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;
import com.studywithus.servlet.user.AuthLogInController;

@WebServlet("/add")
public class ChargeStudyAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao chargeStudyDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    chargeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    Study chargeStudy = new Study();

    chargeStudy.setWriter(AuthLogInController.getLoginUser());
    chargeStudy.setArea(request.getParameter("area"));
    chargeStudy.setTitle(request.getParameter("title"));
    chargeStudy.setContent(request.getParameter("content"));
    chargeStudy.setMaxMembers(Integer.parseInt(request.getParameter("maxMembers")));
    chargeStudy.setPrice(Integer.parseInt(request.getParameter("price")));
    chargeStudy.setStartDate(Date.valueOf(request.getParameter("startedDate")));
    chargeStudy.setEndDate(Date.valueOf(request.getParameter("endDate")));

    try {
      chargeStudyDao.insert(chargeStudy);
      sqlSession.commit();

      response.sendRedirect("list");

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}