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

@WebServlet("/community/list")
public class CommunityListController extends HttpServlet {
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
      // 클라이언트한테 요청받을 때 쓸 변수
      int categoryNo = Integer.parseInt(request.getParameter("no"));

      Collection<Community> communityList = communityDao.findAll(categoryNo);

      request.setAttribute("categoryNo", categoryNo);

      if (communityList == null) {
        request.getRequestDispatcher("CommunityList.jsp").forward(request, response);
      }

      request.setAttribute("communityList", communityList);
      request.getRequestDispatcher("CommunityList.jsp").forward(request, response);

    } catch (Exception e) {
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

  }
}