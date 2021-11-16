package com.studywithus.web.chargestudy;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.studywithus.dao.StudyDao;
import com.studywithus.domain.Member;
import com.studywithus.domain.Study;

@WebServlet("/chargestudy/interestlist")
public class ChargeStudyInterestListController extends HttpServlet {
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
      Member member = (Member) request.getSession().getAttribute("loginUser");

      if (member == null) {
        response.sendRedirect("/swu/user/loginform");
      }

      Collection<Study> chargeStudyList = chargeStudyDao.findAllInterest(member.getNo(),1,10000000);

      request.setAttribute("chargeStudyList", chargeStudyList);

      RequestDispatcher requestDispatcher = request.getRequestDispatcher("ChargeStudyInterestList.jsp");
      requestDispatcher.forward(request, response);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      RequestDispatcher requestDispatcher = request.getRequestDispatcher("/Error.jsp");
      requestDispatcher.forward(request, response);
    }
  }
}