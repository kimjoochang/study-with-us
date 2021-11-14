package com.studywithus.servlet.chargestudy;

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

@WebServlet("/chargestudy/interest/add")
public class ChargeStudyInterestAddController extends HttpServlet {

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

    try {
      int chargeStudyNo = Integer.parseInt(request.getParameter("no"));

      Member member = (Member) request.getSession().getAttribute("loginUser");

      if (member == null) {
        response.sendRedirect("/swu/user/loginform");
      }

      chargeStudyDao.insertInterest(member.getNo(), chargeStudyNo);

      sqlSession.commit();

      response.sendRedirect("../detail?no=" + chargeStudyNo);

      //      request.setAttribute("no", chargeStudyNo);
      //      request.getRequestDispatcher("/chargestudy/detail").forward(request, response);

    } catch (Exception e) {
      sqlSession.rollback();
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

  }
}
