package com.studywithus.servlet.chargestudy;

import java.io.IOException;
import javax.servlet.GenericServlet;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

@WebServlet("/chargeStudy/detail")
public class ChargeStudyDetailController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  StudyDao chargeStudyDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    chargeStudyDao = (StudyDao) servletContext.getAttribute("chargeStudyDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Study chargeStudy = chargeStudyDao.findByNo(no);

      if (chargeStudy == null) {
        throw new Exception("해당 번호의 유료 스터디가 존재하지 않습니다.");
      }

      request.setAttribute("chargeStudy", chargeStudy);
      request.getRequestDispatcher("/chargeStudy/ChargeStudyDetail.jsp").forward(request, response);

    } catch (Exception e) {
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}

