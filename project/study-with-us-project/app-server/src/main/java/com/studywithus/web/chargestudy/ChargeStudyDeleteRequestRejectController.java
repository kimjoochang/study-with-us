package com.studywithus.web.chargestudy;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.DeleteRequestFormDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.DeleteRequestForm;
import com.studywithus.domain.Study;

@WebServlet("/chargestudy/requestreject")

public class ChargeStudyDeleteRequestRejectController  extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao chargeStudyDao;
  SqlSession sqlSession;
  DeleteRequestFormDao deleteRequestFormDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
    chargeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
    deleteRequestFormDao = (DeleteRequestFormDao) servletContext.getAttribute("deleteRequestFormDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      int studyNo = Integer.parseInt(request.getParameter("studyNo"));

      Study chargeStudy = chargeStudyDao.findByNo(studyNo);

      System.out.println(chargeStudy.getTitle());
      chargeStudy.setStudyStatus(0);

      DeleteRequestForm deleteRequestForm = deleteRequestFormDao.findByNo(no);

      deleteRequestForm.setRemarks(request.getParameter("remarks"));

      deleteRequestFormDao.updateRemarks(deleteRequestForm);
      chargeStudyDao.update(chargeStudy);

      sqlSession.commit();

      response.sendRedirect("deleterequestlist");

    } catch (Exception e) {
      System.out.println(e.getMessage());
      sqlSession.rollback();
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}