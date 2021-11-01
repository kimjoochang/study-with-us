package com.studywithus.servlet.community;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;

@WebServlet("/community/updateform")
public class CommunityUpdateFormController extends HttpServlet{

  private static final long serialVersionUID = 1L;

  CommunityDao communityDao;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    communityDao = (CommunityDao) servletContext.getAttribute("communityDao");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      Community community = communityDao.findByNo(Integer.parseInt(request.getParameter("no")));

      if (community == null) {
        throw new Exception("해당 번호의 게시글이 없습니다.");
      }

      request.setAttribute("community", community);
      request.getRequestDispatcher("CommunityUpdateForm.jsp").forward(request, response);

    }catch (Exception e) {
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
