package com.studywithus.servlet.freestudy;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.GenericServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebServlet;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Study;

@WebServlet("/freestudy/list")
public class FreeStudyListController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  StudyDao studyDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    studyDao = (StudyDao) servletContext.getAttribute("studyDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {

    try {
      // 클라이언트한테 요청받을 때 쓸 변수
      Collection<Study> freeStudyList = studyDao.findAll(0, 0);

      request.setAttribute("freeStudyList", freeStudyList);

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("FreeStudyList.jsp");
      requestDispatcher.forward(request, response);

    } catch (Exception e) {

      request.setAttribute("error", e);

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
      requestDispatcher.forward(request, response);
    }

  }
}
