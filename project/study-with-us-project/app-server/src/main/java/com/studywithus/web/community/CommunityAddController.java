package com.studywithus.web.community;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.ibatis.session.SqlSession;
import com.studywithus.dao.CommunityDao;
import com.studywithus.domain.Community;
import com.studywithus.domain.Member;

@WebServlet("/community/add")
public class CommunityAddController extends HttpServlet {
  private static final long serialVersionUID = 1L;

  CommunityDao communityDao;
  SqlSession sqlSession;

  @Override
  public void init(ServletConfig config) throws ServletException {
    ServletContext servletContext = config.getServletContext();
    communityDao = (CommunityDao) servletContext.getAttribute("communityDao");
    sqlSession = (SqlSession) servletContext.getAttribute("sqlSession");
  }

  @Override
  protected void service(HttpServletRequest request, HttpServletResponse response)
      throws ServletException, IOException {

    try {
      int categoryNo = Integer.parseInt(request.getParameter("categoryNo"));

      Member writer = (Member) request.getSession(false).getAttribute("loginUser");

      Community community = new Community();

      community.setCategory(categoryNo);
      community.setTitle(request.getParameter("title"));
      community.setContent(request.getParameter("content"));
      community.setWriter(writer);

      communityDao.insert(community);

      sqlSession.commit();

      //      request.setAttribute("categoryNo", categoryNo);
      //      request.setAttribute("requestByAdd", 0);
      //      request.getRequestDispatcher("list?categoryNo="+categoryNo).forward(request, response);
      response.sendRedirect("list?categoryNo="+categoryNo);
    } catch (Exception e) {
      System.out.println(e.getMessage());
      request.setAttribute("error", e);
      request.getRequestDispatcher("/Error.jsp").forward(request, response);
    }
  }
}
