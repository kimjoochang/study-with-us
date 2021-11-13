package com.studywithus.servlet.chargestudy;

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
import com.studywithus.dao.DeleteRequestFormDao;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.DeleteRequestForm;

@WebServlet("/chargestudy/deleterequestlist")
public class ChargeStudyDeleteRequestListController extends GenericServlet {
  private static final long serialVersionUID = 1L;

  StudyDao chargeStudyDao;
  DeleteRequestFormDao deleteRequestFormDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    chargeStudyDao = (StudyDao) servletContext.getAttribute("studyDao");
    deleteRequestFormDao = (DeleteRequestFormDao) servletContext.getAttribute("deleteRequestFormDao");
  }

  @Override
  public void service(ServletRequest request, ServletResponse response)
      throws ServletException, IOException {
    try {
      Collection<DeleteRequestForm> deleteRequestFormList = deleteRequestFormDao.findAllForAdmin();

      request.setAttribute("deleteRequestFormList", deleteRequestFormList);

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("../AdminPage_study.jsp");
      requestDispatcher.forward(request, response);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
      requestDispatcher.forward(request, response);
    }
  }
}
