package com.studywithus.servlet.chargestudy;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

@WebServlet("/chargestudy/updateform")
public class ChargeStudyUpdateFormController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao chargeStudyDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    chargeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {

      Study chargeStudy = chargeStudyDao.findByNo(Integer.parseInt(request.getParameter("no")));

      request.setAttribute("chargeStudy", chargeStudy);

      request.getRequestDispatcher("/chargestudy/ChargeStudyUpdateForm.jsp").forward(request, response);
    }catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}