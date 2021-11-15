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
import com.studywithus.dao.DeleteRequestFormDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

@WebServlet("/chargestudy/requestapprove")

public class ChargeStudyDeleteRequestApproveController  extends HttpServlet {
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

      Study study = chargeStudyDao.findByNo(studyNo);

      study.setDeleteStatus(2);

      deleteRequestFormDao.delete(no);
      System.out.println(no);
      chargeStudyDao.update(study);
      //System.out.println(studyNo);
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