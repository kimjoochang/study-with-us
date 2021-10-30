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

@WebServlet("/chargestudy/detail")
public class ChargeStudyDetailController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  StudyDao chargeStudyDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    chargeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
  }

  @Override
  public void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int no = Integer.parseInt(request.getParameter("no"));
      Study chargeStudy = chargeStudyDao.findByNo(no);

      if (chargeStudy == null) {
        response.sendRedirect("list");
        throw new Exception("해당 번호의 유료 스터디가 존재하지 않습니다.");
      }

      request.setAttribute("chargeStudy", chargeStudy);
      request.getRequestDispatcher("ChargeStudyDetail.jsp").forward(request, response);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
