package com.studywithus.web.community;

import java.io.IOException;
import java.util.List;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;


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
      int categoryNo;
      // 클라이언트한테 요청받을 때 쓸 변수
      if (request.getAttribute("categoryNo") == null) {

        categoryNo = Integer.parseInt(request.getParameter("categoryNo"));
        request.setAttribute("categoryNo", categoryNo);

      } else {
        categoryNo = (int)request.getAttribute("categoryNo");
        request.setAttribute("categoryNo", categoryNo);
      }

      List<Community> communityList = communityDao.findAll(categoryNo);

      request.setAttribute("communityList", communityList);

      request.getRequestDispatcher("CommunityList.jsp").forward(request, response);

    } catch (Exception e) {
      e.printStackTrace();
      System.out.println(e.getStackTrace());
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }

  }
}