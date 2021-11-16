package com.studywithus.web.chargestudy;

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

@WebServlet("/chargestudy/update")
public class ChargeStudyUpdateController extends HttpServlet {
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
      int no = Integer.parseInt(request.getParameter("no"));

      Study chargeStudy = chargeStudyDao.findByNo(no);

      if (chargeStudy == null) {
        throw new Exception("해당 번호의 회원이 없습니다.");
      } 

      chargeStudy.setTitle(request.getParameter("title"));
      chargeStudy.setArea(chargeStudy.getArea());
      chargeStudy.setContent(request.getParameter("content"));
      chargeStudy.setWriter(chargeStudy.getWriter());
      chargeStudy.setMaxMembers(Integer.parseInt(request.getParameter("maxMembers")));
      chargeStudy.setPrice(chargeStudy.getPrice());
      chargeStudy.setStartDate(Date.valueOf(request.getParameter("startDate")));
      chargeStudy.setEndDate(Date.valueOf(request.getParameter("endDate")));
      chargeStudy.setRegisteredDate(chargeStudy.getRegisteredDate());


      chargeStudyDao.update(chargeStudy);
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
