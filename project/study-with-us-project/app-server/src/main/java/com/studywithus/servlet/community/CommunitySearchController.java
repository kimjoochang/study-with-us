package com.studywithus.servlet.community;

import java.io.IOException;
import java.util.Collection;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;

@WebServlet("/community/search")
public class CommunitySearchController extends HttpServlet  {
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

    String keyword = request.getParameter("keyword");

    int categoryNo = 0;

    try {
      Collection<Community> communityList = communityDao.findByKeyword(keyword, categoryNo);

      request.setAttribute("communityList", communityList);
      request.setAttribute("categoryNo", categoryNo);

      request.getRequestDispatcher("CommunityList.jsp").forward(request, response);

    } catch (Exception e) {
      System.out.println(e.getStackTrace());
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

  }  
}
